package com.medically.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp


@Composable
fun CircleWithIcon(
    modifier: Modifier = Modifier,
    circleSize: Dp,
    circleColor: Color = Color.White,
    iconColor: Color = Color.Unspecified,
    @DrawableRes iconId: Int,
    contentDescription: String?,
) {
    Box(modifier) {
        Circle(size = circleSize, color = circleColor, modifier = modifier)
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier.align(
                Alignment.Center
            ),
            tint = iconColor,
        )
    }
}