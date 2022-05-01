package com.medically.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Circle(modifier: Modifier = Modifier, size: Dp, color: Color) {
    Canvas(modifier = modifier.size(size = size), onDraw = {
        drawCircle(color = color)
    })
}