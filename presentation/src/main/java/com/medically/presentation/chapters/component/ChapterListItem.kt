package com.medically.presentation.chapters.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Chapter
import com.medically.presentation.component.LoadImageWithShimmer
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun ChapterListItem(chapter: Chapter, onChapterSelected: (Chapter) -> Unit) {
    Card(
        backgroundColor = Color.White,
        elevation = 5.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .height(210.dp)
            .width(152.dp)
            .clickable { onChapterSelected(chapter) }
    ) {
        Column(modifier = Modifier) {
            LoadImageWithShimmer(
                imageUrl = chapter.imageUrl,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )
            Text(
                chapter.name,
                modifier = Modifier.padding(horizontal = 10.dp),
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onBackground)
            )
        }
    }
}

@Composable
@Preview
fun PreviewChapterListItem() {
    MedicallyTheme {
        ChapterListItem(chapter = Chapter(name = "Abdomen", id = "")) {}
    }
}

