package com.medically.core.entities

import com.medically.model.Chapter

data class SelectedChapter(
    var isSelected: Boolean = false,
    val chapter: Chapter
)
