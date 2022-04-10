package com.medically.core.chapters

import com.medically.core.entities.BusinessRule
import com.medically.core.entities.Chapter
import kotlinx.coroutines.flow.MutableStateFlow

interface ChaptersPort {
    var chapters: List<Chapter>
    val filteredChapters: MutableStateFlow<List<Chapter>>
    val bindChapters: BusinessRule
}