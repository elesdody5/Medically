package com.medically.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShimmerImage(modifier: Modifier = Modifier) {
    ShimmerAnimation { brush ->
        Spacer(
            modifier = modifier.background(brush)
        )
    }
}