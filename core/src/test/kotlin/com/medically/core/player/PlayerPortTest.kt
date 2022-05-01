package com.medically.core.player

import com.medically.core.entities.BusinessRule
import com.medically.core.integration.MediaConnection
import com.medically.model.NowPlayingMetadata
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlayerPortTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val coroutineScope: CoroutineScope = TestScope(dispatcher)
    private lateinit var adapter: PlayerPort
    private lateinit var state: MutableStateFlow<PlayerPortState>
    private lateinit var position: MutableStateFlow<Long>

    private val nowPlayingMetadata = MutableStateFlow(NowPlayingMetadata("1"))

    @MockK
    private lateinit var mockMusicConnection: MusicServiceConnectionPort

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        MediaConnection.musicServiceConnectionPort = mockMusicConnection

        state = MutableStateFlow(PlayerPortState())
        position = MutableStateFlow(0L)

        adapter = PlayerAdapter(coroutineScope, state, position)
    }

    @Test
    fun onMetaDataChanged_musicConnectionChangeMetaData_stateHasNewMetaData() = runTest {

        //When
        every { mockMusicConnection.nowPlaying } returns nowPlayingMetadata
        nowPlayingMetadata.value = NowPlayingMetadata("2", title = "TestMetaData")

        var expected: String? = null
        state.take(1).collect { expected = it.mediaMetadata?.id }
        assertThat(expected, `is`("2"))
    }
}


class PlayerAdapter(
    override val scope: CoroutineScope,
    override val state: MutableStateFlow<PlayerPortState>,
    override val mediaPosition: MutableStateFlow<Long>
) : PlayerPort {
    override var updatePosition: Boolean = true

    override val bindCollector: BusinessRule = bindCollector()
    override val bindChapter = BusinessRule
    override val bindDoctor = BusinessRule
    override val mediaStateCollector = FlowCollector(::onMetaDataChanged)
    override val playbackStateCollector = FlowCollector(::onPlaybackStateChanged)

}