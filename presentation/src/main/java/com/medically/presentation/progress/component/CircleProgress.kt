package com.medically.presentation.progress.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun CustomCircleProgress(progress: Int) {
    val background = MaterialTheme.colors.secondary
    Box {
        Canvas(modifier = Modifier
            .size(50.dp), onDraw = {
            drawCircle(color = background)
        })
        CircularProgressIndicator(
            progress * 0.01f,
            Modifier.size(50.dp), strokeWidth = 5.dp,
        )
        if (progress != 100) {
            Text(text = "${progress}%", Modifier.align(Alignment.Center))
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_checked),
                contentDescription = "",
                Modifier
                    .align(Alignment.Center)
                    .size(20.dp)
            )
        }
    }


}

@Preview
@Composable
fun PreviewCircle() {
    MedicallyTheme {
        CustomCircleProgress(50)
    }
}

@Preview
@Composable
fun PreviewCircleCompleted() {
    MedicallyTheme {
        CustomCircleProgress(100)
    }
}