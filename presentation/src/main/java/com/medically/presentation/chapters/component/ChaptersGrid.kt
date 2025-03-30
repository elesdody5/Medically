package com.medically.presentation.chapters.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Chapter
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun ChaptersGrid(chapters: List<Chapter>, onChapterSelected: (Chapter) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(chapters) {
            ChapterListItem(chapter = it, onChapterSelected)
        }
    }
}

@Preview
@Composable
fun PreviewChaptersGrid() {
    MedicallyTheme {
        ChaptersGrid(
            chapters = listOf(
                Chapter(name = "Abdomen"),
                Chapter(name = "Abdomen"),
                Chapter(name = "Abdomen"),
                Chapter(name = "Abdomen"),
            )
        ) {}
    }
}