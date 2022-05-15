package com.medically.presentation.downloaded_chapters.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.medically.model.Chapter
import com.medically.presentation.component.list_with_header.Header
import com.medically.presentation.component.list_with_header.ListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DownloadedChaptersList(
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
                ListItem(item = it, name = it.name, onItemSelected = onChapterSelected)
            }
        }
    }
}