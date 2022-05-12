package com.medically.presentation.completed_lectures.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.medically.model.Lecture

@Composable
fun CompletedLecturesList(lectures: List<Lecture>) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(lectures.size) {
            CompletedLectureListItem(it + 1, lecture = lectures[it])
        }
    }
}