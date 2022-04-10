package com.medically.core.years

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data

@BusinessRules
fun YearsPort.searchSubject(subjectName: String) {
    filteredYears.value =
        years.filter { year -> year.subjects.any { subject -> subject.name.contains(subjectName) } }
}

@BusinessRules
fun YearsPort.filterByYears(yearName: String) {
    filteredYears.value = years.filter { year -> year.title == yearName }
}

@BusinessRules
fun YearsPort.filterBySubject(subjectName: String) {
    filteredYears.value =
        years.filter { year -> year.subjects.any { subject -> subject.name == subjectName } }
}

@BusinessRules
suspend fun YearsPort.bindYears() {
    years = Data.yearsRepositoryPort.getAllYears()
    filteredYears.value = years.toList()
}