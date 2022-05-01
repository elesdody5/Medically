package com.medically.media.service

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.medically.core.integration.Data
import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.media.extensions.*
import com.medically.media.notification.MedicallyNotificationManager
import com.medically.media.notification.PlayerNotificationListener
import com.medically.media.notification.state.NotificationState.*
import com.medically.media.service.state.PlayerState
import com.medically.model.AudioPlayList
import kotlinx.coroutines.*


/** Content styling constants */
private const val CONTENT_STYLE_BROWSABLE_HINT = "android.media.browse.CONTENT_STYLE_BROWSABLE_HINT"
private const val CONTENT_STYLE_PLAYABLE_HINT = "android.media.browse.CONTENT_STYLE_PLAYABLE_HINT"
private const val CONTENT_STYLE_SUPPORTED = "android.media.browse.CONTENT_STYLE_SUPPORTED"
private const val CONTENT_STYLE_LIST = 1
private const val CONTENT_STYLE_GRID = 2
const val BROWSABLE_ROOT = "/"
const val EMPTY_ROOT = "@empty@"

const val RECENT_ROOT = "__RECENT__"

const val MEDIA_SEARCH_SUPPORTED = "android.media.browse.SEARCH_SUPPORTED"

/*
 * (Media) Session events
 */
const val NETWORK_FAILURE = "com.medically.media.session.NETWORK_FAILURE"
private val LOG_TAG: String = MediaPlaybackService::class.java.name

class MediaPlaybackService : MediaBrowserServiceCompat() {
    private val job = Job()
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate + job)
    private lateinit var repository: LecturesRepositoryPort
    lateinit var notificationManager: MedicallyNotificationManager

    private lateinit var mediaSession: MediaSessionCompat

    private lateinit var mediaSessionConnector: MediaSessionConnector
    private var currentPlaylistItems: List<MediaMetadataCompat> = emptyList()


    private val dataSourceFactory: DefaultDataSource.Factory by lazy {
        DefaultDataSource.Factory(this)
    }

    private var isForegroundService = false


    private var playerListener: PlayerEventListener = PlayerEventListener()
    private val playerNotificationListener = PlayerNotificationListener()

    private val uAmpAudioAttributes = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()


    /**
     * Configure ExoPlayer to handle audio focus for us.
     * See [Player.AudioComponent.setAudioAttributes] for details.
     */
    private val exoPlayer: ExoPlayer by lazy {
        ExoPlayer.Builder(this).build().apply {
            setAudioAttributes(uAmpAudioAttributes, true)
            setHandleAudioBecomingNoisy(true)
            addListener(playerListener)
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Build a PendingIntent that can be used to launch the UI.
        val sessionActivityPendingIntent =
            packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
                PendingIntent.getActivity(
                    this,
                    0,
                    sessionIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

        // Create a new MediaSession.
        mediaSession = MediaSessionCompat(this, "MediaPlaybackService")
            .apply {
                setSessionActivity(sessionActivityPendingIntent)
                isActive = true
            }

        /**
         * In order for [MediaBrowserCompat.ConnectionCallback.onConnected] to be called,
         * a [MediaSessionCompat.Token] needs to be set on the [MediaBrowserServiceCompat].
         *
         * It is possible to wait to set the session token, if required for a specific use-case.
         * However, the token *must* be set by the time [MediaBrowserServiceCompat.onGetRoot]
         * returns, or the connection will fail silently. (The system will not even call
         * [MediaBrowserCompat.ConnectionCallback.onConnectionFailed].)
         */
        sessionToken = mediaSession.sessionToken


        // ExoPlayer will manage the MediaSession for us.
        mediaSessionConnector = MediaSessionConnector(mediaSession)
        mediaSessionConnector.setQueueNavigator(QueueNavigator(mediaSession))


        notificationManager = MedicallyNotificationManager(
            this,
            mediaSession.sessionToken,
            playerNotificationListener,
            coroutineScope
        )

        repository = Data.lecturesRepository
        coroutineScope.launch { observeNotificationState() }
        coroutineScope.launch { observePlayerEvent() }
    }

    /**
     * This is the code that causes to stop playing when swiping the activity away from
     * recents. The choice to do this is app specific. Some apps stop playback, while others allow
     * playback to continue and allow users to stop it with the notification.
     */
    override fun onTaskRemoved(rootIntent: Intent) {
        super.onTaskRemoved(rootIntent)
        exoPlayer.stop()
    }


    override fun onDestroy() {
        mediaSession.run {
            isActive = false
            release()
        }

        // Free ExoPlayer resources.
        exoPlayer.removeListener(playerListener)
        exoPlayer.release()
        job.cancel()
    }

    /**
     * Returns the "root" media ID that the client should request to get the list of
     * [MediaItem]s to browse/play.
     */
    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot {
        coroutineScope.launch {
            repository.getCurrentPlayList().collect { playList ->
                if (playList.lectures?.isNotEmpty() == true) {
                    prepareList(playList)
                    initializePlayer(
                        itemToPlay = currentPlaylistItems[playList.currentPlayingPosition ?: 0]
                    )
                    notificationManager.showNotificationForPlayer(exoPlayer)
                }
            }
        }
        /**
         * By default return the browsable root. Treat the EXTRA_RECENT flag as a special case
         * and return the recent root instead.
         */
        val isRecentRequest = rootHints?.getBoolean(BrowserRoot.EXTRA_RECENT) ?: false
        val browserRootPath = if (isRecentRequest) RECENT_ROOT else BROWSABLE_ROOT
        return BrowserRoot(browserRootPath, null)

    }

    /**
     * Returns (via the [result] parameter) a list of [MediaItem]s that are child
     * items of the provided [parentMediaId]. See [BrowseTree] for more details on
     * how this is build/more details about the relationships.
     */
    override fun onLoadChildren(
        parentMediaId: String,
        result: Result<List<MediaItem>>
    ) {

        /**
         * If the caller requests the recent root, return the most recently played song.
         */
        Log.i(LOG_TAG, "onLoadChildren")
        val children = currentPlaylistItems.map { item ->
            MediaItem(item.description, item.flag)
        }
        result.sendResult(children)
    }


    /**
     * Load the supplied list of songs and the song to play into the current player.
     */
    private fun prepareList(audioPlayList: AudioPlayList) {
        audioPlayList.apply {
            val mediaMetadataCompat =
                MediaMetadataCompat.Builder()
                    .subject(subject?.name)
                    .doctor(doctor?.name)
                    .chapter(chapter)

            currentPlaylistItems = lectures?.mapIndexed { index, lecture ->
                mediaMetadataCompat.lecture(lecture, index, audioPlayList.lectures?.size).build()
            } ?: emptyList()
        }
    }


    private fun initializePlayer(
        itemToPlay: MediaMetadataCompat?,
        playbackStartPositionMs: Long = 0
    ) {
        // Since the playlist was probably based on some ordering (such as tracks
        // on an album), find which window index to play first so that the song the
        // user actually wants to hear plays first.
        val initialWindowIndex =
            if (itemToPlay == null) 0 else currentPlaylistItems.indexOf(itemToPlay)
        exoPlayer.playWhenReady = true
        val mediaSource = currentPlaylistItems.toMediaSource(dataSourceFactory)
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()
        exoPlayer.seekTo(initialWindowIndex, playbackStartPositionMs)
        mediaSessionConnector.setPlayer(exoPlayer)
    }

    private suspend fun observeNotificationState() {
        playerNotificationListener.notificationState.collect {
            when (it) {
                is NotificationPosted -> onNotificationPosted(
                    it.ongoing,
                    it.notificationId,
                    it.notification
                )
                is NotificationCancelled -> onNotificationCancelled()
                else -> {}
            }
        }
    }

    private suspend fun observePlayerEvent() {
        playerListener.playerState.collect {
            when (it) {
                is PlayerState.IsReady -> onPlayerReady(it.playWhenReady)
                is PlayerState.NotReady -> notificationManager.hideNotification()
                is PlayerState.Error -> Toast.makeText(
                    applicationContext,
                    it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun onNotificationPosted(
        ongoing: Boolean,
        notificationId: Int,
        notification: Notification
    ) {
        if (ongoing && !isForegroundService) {
            ContextCompat.startForegroundService(
                applicationContext,
                Intent(applicationContext, this@MediaPlaybackService.javaClass)
            )

            startForeground(notificationId, notification)
            isForegroundService = true
        }
    }

    private fun onNotificationCancelled() {
        stopForeground(true)
        isForegroundService = false
        stopSelf()
    }

    private fun onPlayerReady(playWhenReady: Boolean) {
        notificationManager.showNotificationForPlayer(exoPlayer)
        if (!playWhenReady) {
            // If playback is paused we remove the foreground state which allows the
            // notification to be dismissed. An alternative would be to provide a
            // "close" button in the notification which stops playback and clears
            // the notification.
            stopForeground(false)
            isForegroundService = false
        }
    }

    private inner class QueueNavigator(
        mediaSession: MediaSessionCompat
    ) : TimelineQueueNavigator(mediaSession) {
        override fun getMediaDescription(
            player: Player,
            windowIndex: Int
        ): MediaDescriptionCompat =
            currentPlaylistItems[windowIndex].description
    }
}
