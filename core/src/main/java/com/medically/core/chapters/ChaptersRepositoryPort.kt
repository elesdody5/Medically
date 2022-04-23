package com.medically.core.chapters

import com.medically.core.entities.BusinessRule
import com.medically.model.Chapter
import com.medically.model.Result
import kotlinx.coroutines.flow.MutableStateFlow

interface ChaptersRepositoryPort {
    suspend fun getChapters(doctorName: String): Result<List<Chapter>>
}
