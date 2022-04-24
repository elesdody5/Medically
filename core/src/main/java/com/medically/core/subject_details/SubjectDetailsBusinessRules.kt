package com.medically.core.subject_details

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data
import com.medically.model.Result
import kotlinx.coroutines.launch

@BusinessRules
fun SubjectDetailsPort.bindDoctors() {
    scope.launch {
        val result = Data.doctorsRepository.getDoctors()
        if (result is Result.Success) {
            state.value = state.value.copy(
                isLoading = false,
                doctors = result.data
            )
            bindDoctorMaterials(result.data.first().name ?: "")
        }
    }
}

@BusinessRules
fun SubjectDetailsPort.bindCurrentSubject() {
    val subject = Data.subjectsRepositoryPort.getCurrentSubject()
    state.value = state.value.copy(subject = subject)
}

@BusinessRules
fun SubjectDetailsPort.bindDoctorMaterials(doctorName: String) {
    bindChapters(doctorName)
    bindPdfs(doctorName)
    bindVideos(doctorName)
}

@BusinessRules
fun SubjectDetailsPort.bindChapters(doctorName: String) {
    state.value = state.value.copy(isLoading = true)
    scope.launch {
        val result = Data.subjectDetailsRepository.getChapters(doctorName)
        if (result is Result.Success) {
            state.value = state.value.copy(
                isLoading = false,
                chapters = result.data,
                filteredChapters = result.data.toList()
            )
        }
    }
}

@BusinessRules
fun SubjectDetailsPort.bindPdfs(doctorName: String) {
    state.value = state.value.copy(isLoading = true)
    scope.launch {
        val result = Data.subjectDetailsRepository.getPdfs(doctorName)
        if (result is Result.Success) {
            state.value = state.value.copy(
                isLoading = false,
                pdfs = result.data,
            )
        }
    }
}

@BusinessRules
fun SubjectDetailsPort.bindVideos(doctorName: String) {
    state.value = state.value.copy(isLoading = true)
    scope.launch {
        val result = Data.subjectDetailsRepository.getVideos(doctorName)
        if (result is Result.Success) {
            state.value = state.value.copy(
                isLoading = false,
                videos = result.data,
            )
        }
    }
}