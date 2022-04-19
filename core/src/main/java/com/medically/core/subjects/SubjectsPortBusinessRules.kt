package com.medically.core.subjects

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data
import com.medically.model.Result
import kotlinx.coroutines.launch

@BusinessRules
fun SubjectsPort.bindSubjects() {
    val subjectsRepository = Data.subjectsRepositoryPort
    state.value = state.value.copy(isLoading = true)
    scope.launch {
        val result = subjectsRepository.getAllSubjects()
        if (result is Result.Success) {
            val subjects = result.data?.groupBy { it.yearName } ?: emptyMap()
            state.value =
                SubjectsPortState(
                    subjects = subjects,
                    filteredSubjects = subjects,
                    isLoading = false
                )
        }
    }
}

@BusinessRules
fun SubjectsPort.searchSubject(subjectName: String) {
    val allSubjects = state.value.subjects.values.flatten()
    val filtered =
        allSubjects.filter { subject -> subject.name.contains(subjectName, ignoreCase = true) }
    state.value =
        state.value.copy(filteredSubjects = filtered.groupBy { it.yearName })
}

@BusinessRules
 fun SubjectsPort.filterByYears(yearName: String) {
    val filtered = state.value.subjects.filter { it.key.equals(yearName, ignoreCase = true) }
    state.value = state.value.copy(filteredSubjects = filtered)
}

@BusinessRules
fun SubjectsPort.filterBySubject(subjectName: String) {
    val allSubjects = state.value.subjects.values.flatten()
    val filtered =
        allSubjects.filter { subject -> subject.name.equals(subjectName, ignoreCase = true) }
    state.value =
        state.value.copy(filteredSubjects = filtered.groupBy { it.yearName })
}