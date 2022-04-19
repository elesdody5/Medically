package com.medically.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue,
    primaryVariant = Blue_0xFFE5ECFF,
    surface = Blue_0xFFE5ECFF,
    onBackground = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = Blue_0xFFE5ECFF,
    secondary = Gray_0xffF2F4F7,
    secondaryVariant = Gray_0xff636363,
    surface = Blue_0xFFE5ECFF,
    onBackground = Color.Black,
    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    */
)

@Composable
fun MedicallyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}