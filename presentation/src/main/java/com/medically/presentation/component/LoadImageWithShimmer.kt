package com.medically.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun LoadImageWithShimmer(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    @DrawableRes placeholder: Int? = null
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(placeholder ?: 0)
            .crossfade(true)
            .crossfade(300)
            .allowHardware(true)
            .build(),
        contentScale = ContentScale.Fit,
        modifier = modifier,
        contentDescription = "chapterImage",
        loading = {
            ShimmerImage(modifier)
        }
    )
}