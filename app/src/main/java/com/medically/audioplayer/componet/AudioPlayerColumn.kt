package com.medically.audioplayer.componet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.medically.domain.model.Chapter
import com.medically.domain.model.Lecture
import com.medically.ui.theme.PrimaryColor
import com.medically.utils.loadPicture

@Composable
fun AudioPlayerColumn(
    lecture: Lecture?,
    chapter: Chapter?,
    doctorName: String?
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val image = chapter?.imageUrl?.let { loadPicture(url = it).value }
        image?.let { img ->
            Image(
                bitmap = img.asImageBitmap(),
                contentDescription = "Chapter Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(10))                       // clip to the circle shape
            )
            Text(
                text = doctorName ?: "",
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
            )

            Text(
                text = lecture?.name ?: "",
                style = TextStyle(color = PrimaryColor, fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AudioPlayerColumnPreview() {
    AudioPlayerColumn(
        lecture = null,
        chapter = Chapter(
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/medrecord-783c6.appspot.com/o/FifthYear%2FInternal%20Medicine%2FDr.%20Ahmed%20Mowafy%2FCardiology%2FCardiology.png?alt=media&token=22141ce8-dcde-4dff-8704-7be555a36287",
            name = ""
        ),
        doctorName = ""
    )
}