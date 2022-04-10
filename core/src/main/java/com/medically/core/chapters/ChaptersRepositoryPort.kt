package com.medically.core.chapters

import com.medically.core.entities.BusinessRule
import com.medically.core.entities.Chapter
import kotlinx.coroutines.flow.MutableStateFlow

interface ChaptersRepositoryPort {
    suspend fun getChapters(): List<Chapter>

}
