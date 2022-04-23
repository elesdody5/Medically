package com.medically.presentation.video

import androidx.compose.runtime.Composable
import com.google.accompanist.web.WebContent
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.medically.model.Video
import com.medically.presentation.R
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.LoadingProgress

@Composable
fun VideoScreen(
    isLoading: Boolean,
    videos: List<Video>
) {
    val webViewState =
        WebViewState(
            WebContent.Url(
                url = videos.firstOrNull()?.url ?: "",
            )
        )

    if (isLoading)
        LoadingProgress()
    if (videos.isEmpty() && !isLoading)
        EmptyTextMessage(iconId = R.drawable.ic_pdf, message = R.string.no_video)
    if (!isLoading)
        WebView(webViewState,
            onCreated = { it.settings.javaScriptEnabled = true }
        )
}