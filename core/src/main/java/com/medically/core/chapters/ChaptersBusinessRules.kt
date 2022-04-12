package com.medically.core.chapters

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data

@BusinessRules
suspend fun ChaptersPort.bindChapters() {
    chapters = Data.chaptersRepository.getChapters()
    filteredChapters.value = chapters.toList()
}