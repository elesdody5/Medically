package com.medically.core.chapters

import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.integration.Data
import com.medically.core.subject_details.SubjectDetailsRepositoryPort
import com.medically.model.Chapter
import com.medically.model.Doctor
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
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
class ChaptersBusinessRulesTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val coroutineScope: CoroutineScope = TestScope(dispatcher)

    @MockK
    private lateinit var mockChaptersRepo: SubjectDetailsRepositoryPort

    @MockK
    private lateinit var mockDoctorsRepo: DoctorsRepositoryPort

    private lateinit var adapter: ChaptersAdapter
    private val chapters =
        listOf<Chapter>(
            Chapter(name = "chapter1", doctorName = "ahmed"),
            Chapter(name = "chapter2", doctorName = "mohamed")
        )
    private val doctors =
        listOf<Doctor>(
            Doctor(name = "ahmed", subjectId = "1"),
            Doctor(name = "mohamed", subjectId = "1")
        )


    private val state = MutableStateFlow(ChaptersPortState())

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Data.subjectDetailsRepository = mockChaptersRepo
        Data.doctorsRepository = mockDoctorsRepo

        adapter = ChaptersAdapter(scope = coroutineScope, state = state)
    }

    @After
    fun clear() {
        clearAllMocks()
    }



    @Test
    fun searchChapter() {
        state.value = state.value.copy(chapters = chapters)
        adapter.searchChapter("1")
        val filteredChapters = state.value.filteredChapters
        assertThat(filteredChapters.size, `is`(1))
    }
}

class ChaptersAdapter(
    override val state: MutableStateFlow<ChaptersPortState>,
    override val scope: CoroutineScope
) : ChaptersPort



