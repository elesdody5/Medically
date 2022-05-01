package com.medically.core.subjects

import com.medically.core.entities.BusinessRule
import com.medically.core.integration.Data
import com.medically.model.Result
import com.medically.model.Subject
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SubjectsPortBusinessRuleTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val coroutineScope: CoroutineScope = TestScope(dispatcher)

    @MockK
    private lateinit var mockSubjectsRepo: SubjectsRepositoryPort
    private lateinit var adapter: SubjectsPort
    private val subjects =
        listOf(
            Subject(name = "subject1", yearName = "first"),
            Subject(name = "subject2", yearName = "second")
        )


    private val state = MutableStateFlow(SubjectsPortState())

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Data.subjectsRepositoryPort = mockSubjectsRepo

        adapter = SubjectsAdapter(coroutineScope, state)
    }

    @After
    fun clear() {
        clearAllMocks()
    }

    @Test
    fun searchSubject() {
        state.value = state.value.copy(subjects = subjects.groupBy { it.yearName })
        adapter.searchSubject("1")
        val filteredSubjects = state.value.filteredSubjects
        assertThat(filteredSubjects.size, `is`(1))
    }

    @Test
    fun filterByYears() {
        state.value = state.value.copy(subjects = subjects.groupBy { it.yearName })
        adapter.filterByYears("first")
        val filteredSubjects = state.value.filteredSubjects
        val yearTitle = filteredSubjects.keys.first()
        assertThat(yearTitle, IsEqual("first"))
    }

    @Test
    fun filterBySubject_subjectNameNotMatchSubjectFullName_filteredYearsIsEmpty() {
        adapter.filterBySubject("1")
        val filteredSubjects = state.value.filteredSubjects
        assertThat(filteredSubjects.isEmpty(), `is`(true))
    }

    @Test
    fun filterBySubject_subjectNameMatchSubjectFullName_filteredYears() {
        state.value = state.value.copy(subjects = subjects.groupBy { it.yearName })
        adapter.filterBySubject("subject1")
        val filteredSubjects = state.value.filteredSubjects
        assertThat(filteredSubjects.size, `is`(1))
    }

    @Test
    fun bindSubjects() {
        coEvery { mockSubjectsRepo.getAllSubjects() } returns Result.Success(subjects)
        adapter.bindSubjects()
        dispatcher.scheduler.runCurrent()
        coVerifyOrder { mockSubjectsRepo.getAllSubjects() }
    }
}

class SubjectsAdapter(
    override val scope: CoroutineScope,
    override val state: MutableStateFlow<SubjectsPortState>,
) : SubjectsPort {
    override val bindSubjects: BusinessRule = Unit
    override val bindCurrentPlay: BusinessRule = Unit
}
