package com.medically.presentation.completed_lectures.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.medically.model.Lecture
import com.medically.presentation.R

@Composable
fun CompletedLectureListItem(index: Int, lecture: Lecture) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "${index}. ${lecture.name}",
            color = MaterialTheme.colors.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(8.dp)
                .weight(5f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_checked),
            contentDescription = "check",
            modifier = Modifier.weight(1f)
        )
    }
}