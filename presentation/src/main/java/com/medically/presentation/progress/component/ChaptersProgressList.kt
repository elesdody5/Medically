package com.medically.presentation.progress.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Chapter
import com.medically.presentation.component.list_with_header.Header
import com.medically.presentation.ui.theme.MedicallyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChaptersProgressList(
    chapters: Map<String, List<Chapter>>,
    onChapterSelected: (Chapter) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        chapters.forEach { (doctor, doctorChapters) ->
            stickyHeader {
                Header(doctor)
            }
            items(doctorChapters) {
                ChapterProgressLitItem(chapter = it, onChapterSelected)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChaptersProgressPreview() {
    MedicallyTheme {
        ChaptersProgressList(
            chapters = mapOf(
                "doctor" to listOf(
                    Chapter(
                        name = "chapter",
                        progress = 50,
                        imageUrl = "",
                        doctorName = "",
                        lecturesCount = 0
                    )
                )
            )
        ) {}
    }
}