package com.medically.core.subjects

import com.medically.model.Result
import com.medically.model.Subject
import com.medically.model.Year

interface SubjectsRepositoryPort {
    suspend fun getAllSubjects(): Result<List<Subject>?>

}