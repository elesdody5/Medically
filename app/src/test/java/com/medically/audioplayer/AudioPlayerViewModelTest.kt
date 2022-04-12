package com.medically.audioplayer

import android.app.Application
import android.content.ComponentName
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import com.medically.MainCoroutineRule
import com.medically.extensions.id
import com.medically.mediaservice.MediaPlaybackService
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class AudioPlayerViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AudioPlayerViewModel

    @RelaxedMockK
    lateinit var application: Application

    lateinit var mediaConnection: MusicServiceConnection


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mediaConnection = MusicServiceConnection(
            application.applicationContext,
            ComponentName(application.applicationContext, MediaPlaybackService::class.java)
        )
        viewModel = AudioPlayerViewModel(application, mediaConnection)

    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    @Ignore
    fun playbackStateChange_updatePlayButton() {

        // When playbackState change
        val state = PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_PLAYING, 0, 0f)
            .build()

        mediaConnection.playbackState.value = state
        //Then change mutable state of button
        assertEquals(viewModel.mediaButtonRes, Icons.Filled.Pause)

    }

    @Test
    @Ignore
    fun mediaMetaDataChange_updateCurrentMediaMetaData() {


        // When mediaMetaData change
        val mediaMetadata =
            MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, "id")
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, 10)
                .build()

        mediaConnection.nowPlaying.value = mediaMetadata

        //Then change mutable state of media state
        assertEquals(mediaMetadata.id, viewModel.mediaMetadata?.id)

    }

//    @Test
//    fun checkPlaybackPosition_changeMediaPlaybackPosition_updateStatePosition() = runTest {
//        // When playbackState change
//        val state =
//            PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_PLAYING, 20, 0f)
//                .build()
//        mediaConnection.playbackState.value = state
//
//        assertEquals(20, viewModel.mediaPosition)
//        delay(100)
//
//        val secondState =
//            PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_PLAYING, 50, 0f)
//                .build()
//        mediaConnection.playbackState.value = secondState
//
//        assertEquals(50, viewModel.mediaPosition)
//    }
}