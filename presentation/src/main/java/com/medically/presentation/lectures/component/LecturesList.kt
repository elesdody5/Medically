package com.medically.presentation.lectures.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Lecture
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun LecturesList(lectures: List<Lecture>, onLectureSelected: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 10.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        items(lectures.size) { index ->
            LectureListItem(lectures[index], index) {
                onLectureSelected(index)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLecturesList() {
    MedicallyTheme {
        LecturesList(lectures = listOf(
            Lecture("01", "Lecture", "", ""),
            Lecture("01", "Lecture", "", ""),
            Lecture("01", "Lecture", "", ""),
        ), onLectureSelected = {})
    }
}