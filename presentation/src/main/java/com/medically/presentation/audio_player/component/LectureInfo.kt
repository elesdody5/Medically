package com.medically.presentation.audio_player.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.component.LoadImageWithShimmer
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun LectureInfo(
    imageUrl: String,
    lectureTitle: String,
    doctorName: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoadImageWithShimmer(
            imageUrl = imageUrl,
            modifier = Modifier
                .padding(vertical = 25.dp)
                .size(280.dp)
                .clip(RoundedCornerShape(10.dp)),
        )
        Text(
            lectureTitle,
            style = MaterialTheme.typography.h2.copy(MaterialTheme.colors.onBackground),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            doctorName,
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.subtitle1.copy(MaterialTheme.colors.onBackground)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLectureInfo() {
    MedicallyTheme {
        LectureInfo(
            imageUrl = "",
            lectureTitle = "Lecture 1",
            doctorName = "Dr. Ahmed"
        )
    }
}