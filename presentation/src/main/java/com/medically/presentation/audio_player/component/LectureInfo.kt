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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Lecture
import com.medically.presentation.R
import com.medically.presentation.component.LoadImageWithShimmer
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun LectureInfo(imageUrl: String, lecture: Lecture, doctorName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoadImageWithShimmer(
            imageUrl = imageUrl,
            placeholder = R.drawable.audio_placeholder,
            modifier = Modifier
                .padding(vertical = 25.dp)
                .size(280.dp)
                .clip(RoundedCornerShape(10.dp)),
        )
        Text(
            "${lecture.number ?: ""}. ${lecture.name}",
            style = MaterialTheme.typography.h2.copy(MaterialTheme.colors.onBackground)
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
        LectureInfo(imageUrl = "", lecture = Lecture("01", "Lecture 1"), doctorName = "Dr. Ahmed")
    }
}