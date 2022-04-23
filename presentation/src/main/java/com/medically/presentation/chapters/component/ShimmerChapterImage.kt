package com.medically.presentation.chapters.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.medically.presentation.component.ShimmerAnimation

@Composable
fun ShimmerChapterImage() {
    ShimmerAnimation { brush ->
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(147.dp)
                .width(147.dp)
                .padding(8.dp)
                .background(brush = brush, RoundedCornerShape(5.dp))
        )
    }
}