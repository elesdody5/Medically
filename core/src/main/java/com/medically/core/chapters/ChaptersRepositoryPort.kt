package com.medically.core.chapters

import com.medically.core.entities.BusinessRule
import com.medically.model.Chapter
import kotlinx.coroutines.flow.MutableStateFlow

interface ChaptersRepositoryPort {
    fun getChapters(): List<Chapter>
}
