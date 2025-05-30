/*
 * Copyright 2020 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.medically.media.notification

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.medically.media.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val NOW_PLAYING_CHANNEL_ID = "com.medically.NOW_PLAYING"
const val NOW_PLAYING_NOTIFICATION_ID = 100 // Arbitrary number used to identify our notification

/**
 * A wrapper class for ExoPlayer's PlayerNotificationManager. It sets up the notification shown to
 * the user during audio playback and provides track metadata, such as track title and icon image.
 */
class MedicallyNotificationManager(
    private val context: Context,
    sessionToken: MediaSessionCompat.Token,
    notificationListener: PlayerNotificationManager.NotificationListener,
    private val serviceScope: CoroutineScope
) {

    private val notificationManager: PlayerNotificationManager

    init {
        val mediaController = MediaControllerCompat(context, sessionToken)
        notificationManager = PlayerNotificationManager.Builder(
            context, NOW_PLAYING_NOTIFICATION_ID,
            NOW_PLAYING_CHANNEL_ID
        ).setChannelNameResourceId(R.string.notification_channel)
            .setChannelDescriptionResourceId(R.string.notification_channel_description)
            .setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
            .setNotificationListener(notificationListener)
            .build().apply {
                setMediaSessionToken(sessionToken)
                setSmallIcon(R.drawable.ic_notification_icon)
            }
    }


    fun hideNotification() {
        notificationManager.setPlayer(null)
    }

    fun showNotificationForPlayer(player: Player) {
        notificationManager.setPlayer(player)
    }

    private inner class DescriptionAdapter(private val controller: MediaControllerCompat) :
        PlayerNotificationManager.MediaDescriptionAdapter {

        var currentIconUri: Uri? = null
        var currentBitmap: Bitmap? = null

        override fun createCurrentContentIntent(player: Player): PendingIntent? =
            controller.sessionActivity

        override fun getCurrentContentText(player: Player) =
            controller.metadata.description.subtitle.toString()

        override fun getCurrentContentTitle(player: Player) =
            controller.metadata.description.title.toString()

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            val iconUri = controller.metadata.description.iconUri
            return if (currentIconUri != iconUri || currentBitmap == null) {

                // Cache the bitmap for the current song so that successive calls to
                // `getCurrentLargeIcon` don't cause the bitmap to be recreated.
                currentIconUri = iconUri
                serviceScope.launch {
                    currentBitmap = iconUri?.let {
                        resolveUriAsBitmap(it)
                    }
                    currentBitmap?.let { callback.onBitmap(it) }
                }
                null
            } else {
                currentBitmap
            }
        }

        private suspend fun resolveUriAsBitmap(uri: Uri): Bitmap? {
            return withContext(Dispatchers.IO) {
                // Block on downloading artwork.
                val loader = ImageLoader(context)
                val request = ImageRequest.Builder(context)
                    .data(uri)
                    .allowHardware(false) // Disable hardware bitmaps.
                    .size(NOTIFICATION_LARGE_ICON_SIZE)
                    .build()

                val result = loader.execute(request)
                if (result is SuccessResult) {
                    val bitmap = (result.drawable as BitmapDrawable).bitmap
                    bitmap
                } else {
                    null
                }
            }
        }
    }
}

const val NOTIFICATION_LARGE_ICON_SIZE = 144 // px


private const val MODE_READ_ONLY = "r"
