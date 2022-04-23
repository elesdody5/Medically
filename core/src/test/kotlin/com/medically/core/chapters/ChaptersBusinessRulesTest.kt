package com.medically.core.chapters

import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.entities.BusinessRule
import com.medically.core.integration.Data
import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.Result
import com.medically.model.Subject
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
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
    private lateinit var mockChaptersRepo: ChaptersRepositoryPort

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
        Data.chaptersRepository = mockChaptersRepo
        Data.doctorsRepository = mockDoctorsRepo

        adapter = ChaptersAdapter(scope = coroutineScope, state = state)
    }

    @After
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun bindChapters_getChaptersFromRepo() {
        coEvery {
            mockChaptersRepo.getChapters(
                chapters.first().doctorName ?: ""
            )
        } returns Result.Success(listOf(chapters.first()))
        adapter.bindChapters(chapters.first().doctorName ?: "")
        val expected = adapter.state.value.chapters
        assertThat(expected.size, `is`(1))
    }

    @Test
    fun bindDoctors() {
        coEvery {
            mockDoctorsRepo.getDoctors()
        } returns Result.Success(doctors)
        adapter.bindDoctors("first", Subject(id = "1"))
        val expected = adapter.state.value.doctors
        assertThat(expected.size, `is`(doctors.size))
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
) : ChaptersPort {
    override val bindDoctors: BusinessRule = Unit
}


