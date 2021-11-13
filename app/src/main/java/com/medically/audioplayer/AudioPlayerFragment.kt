package com.medically.audioplayer

import android.content.ComponentName
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.medically.R
import com.medically.mediasession.MediaPlaybackService


class AudioPlayerFragment : Fragment() {
    private lateinit var mediaBrowser: MediaBrowserCompat
    private var controllerCallback = object : MediaControllerCompat.Callback() {

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {}

        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_audio_player, container, false)
        mediaBrowser = MediaBrowserCompat(
            requireContext(),
            ComponentName(requireContext(), MediaPlaybackService::class.java),
            connectionCallbacks,
            null // optional Bundle
        )

        return view
    }


    public override fun onStart() {
        super.onStart()
        mediaBrowser.connect()
    }

    public override fun onResume() {
        super.onResume()
//        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    public override fun onStop() {
        super.onStop()
        // (see "stay in sync with the MediaSession")
        MediaControllerCompat.getMediaController(requireActivity())
            ?.unregisterCallback(controllerCallback)
        mediaBrowser.disconnect()
    }

    private val connectionCallbacks = object : MediaBrowserCompat.ConnectionCallback() {
        override fun onConnected() {

            // Get the token for the MediaSession
            mediaBrowser.sessionToken.also { token ->

                // Create a MediaControllerCompat
                val mediaController = MediaControllerCompat(
                    requireContext(), // Context
                    token
                )

                // Save the controller
                MediaControllerCompat.setMediaController(requireActivity(), mediaController)
            }

//            // Finish building the UI
//            buildTransportControls()
        }

        override fun onConnectionSuspended() {
            // The Service has crashed. Disable transport controls until it automatically reconnects
        }

        override fun onConnectionFailed() {
            // The Service has refused our connection
        }
    }
}