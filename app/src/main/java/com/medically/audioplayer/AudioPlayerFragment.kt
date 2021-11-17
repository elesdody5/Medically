package com.medically.audioplayer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.medically.audioplayer.componet.AudioPlayerColumn
import com.medically.domain.model.Chapter


@Composable
fun AudioPlayerScreen() {
    val viewModel = hiltViewModel<AudioPlayerViewModel>()
    AudioPlayerColumn(
        lecture = null, chapter = Chapter(
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/medrecord-783c6.appspot.com/o/FifthYear%2FInternal%20Medicine%2FDr.%20Ahmed%20Mowafy%2FCardiology%2FCardiology.png?alt=media&token=22141ce8-dcde-4dff-8704-7be555a36287",
            name = ""
        ), doctorName = "Ahmed"
    )
}