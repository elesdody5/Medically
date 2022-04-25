package com.medically.presentation.lectures.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Lecture
import com.medically.presentation.R
import com.medically.presentation.component.CircleWithIcon
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun LectureListItem(lecture: Lecture, onLectureSelected: (Lecture) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_audo),
                tint = MaterialTheme.colors.primary,
                contentDescription = "lec",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                "${lecture.number ?: ""}.",
                color = MaterialTheme.colors.onBackground,
            )
            Text(
                lecture.name ?: "",
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CircleWithIcon(
                iconId = R.drawable.ic_download,
                circleSize = 35.dp,
                circleColor = MaterialTheme.colors.secondary,
                contentDescription = null
            )
            CircleWithIcon(
                iconId = R.drawable.ic_baseline_bookmark_border_24,
                circleSize = 35.dp,
                circleColor = MaterialTheme.colors.secondary,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLectureItem() {
    MedicallyTheme {
        LectureListItem(lecture = Lecture("01", "Lecture"), onLectureSelected = {})
    }
}