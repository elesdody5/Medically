package com.medically.core.subjects

import com.medically.model.Result
import com.medically.model.Subject

interface SubjectsRepositoryPort {
    suspend fun getAllSubjects(): Result<List<Subject>?>
    fun saveCurrentSubject(subject: Subject)
    fun getCurrentSubject(): Subject?
}