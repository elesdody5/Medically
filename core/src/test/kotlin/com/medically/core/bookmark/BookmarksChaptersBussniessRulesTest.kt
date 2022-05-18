package com.medically.core.bookmark

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.core.entities.BusinessRule
import com.medically.core.integration.Data
import com.medically.model.Chapter
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookmarksChaptersBussniessRulesTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val coroutineScope: CoroutineScope = TestScope(dispatcher)
    private val state = MutableStateFlow(BookmarksChaptersState())
    private val chapters = listOf(
        Chapter("doctor1", "1"),
        Chapter("doctor1", "2"),
        Chapter("doctor2", "1"),
        Chapter("doctor2", "2"),
    )

    @MockK
    private lateinit var chaptersRepositoryPort: ChaptersRepositoryPort

    private lateinit var adapter: ChaptersAdapter
    private lateinit var bookmarkedFlow: MutableStateFlow<List<Chapter>>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        bookmarkedFlow = MutableStateFlow(chapters)
        every { chaptersRepositoryPort.getBookmarksChapters() } returns bookmarkedFlow
        adapter = ChaptersAdapter(coroutineScope, state)
        Data.chaptersRepository = chaptersRepositoryPort
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun bindBookmarkedChapters() {
        every { chaptersRepositoryPort.getBookmarksChapters() } returns bookmarkedFlow
        adapter.bindBookmarkedChapters()
        assertThat(state.value.chapters["doctor1"]?.size, `is`(2))
        assertThat(state.value.chapters["doctor2"]?.size, `is`(2))
    }

    @Test
    fun removeSelectedChapters() {
        adapter.bindBookmarkedChapters()
        val allChapters = state.value.chapters["doctor1"]
        allChapters?.forEach { it.isSelected = true }
        coEvery { chaptersRepositoryPort.removeBookmarkChapter(any()) } just Runs
        adapter.removeSelectedChapters()
        val selected = allChapters?.map { it.chapter }
        coVerify { chaptersRepositoryPort.removeBookmarkChapter(*selected?.toTypedArray()!!) }
    }

}

class ChaptersAdapter(
    override val scope: CoroutineScope,
    override val state: MutableStateFlow<BookmarksChaptersState>
) : BookmarksChaptersPort {
    override val bindBookmarksChapters: BusinessRule
        get() = bindBookmarkedChapters()

}