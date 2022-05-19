package com.medically.presentation.video

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.medically.model.Video
import com.medically.presentation.R
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.LoadingProgress

@Composable
fun VideoScreen(
    isLoading: Boolean,
    videos: List<Video>
) {

    var video by remember(videos) {
        mutableStateOf(videos.firstOrNull())
    }



    if (isLoading)
        LoadingProgress()
    if (videos.isEmpty() && !isLoading)
        EmptyTextMessage(iconId = R.drawable.ic_pdf, message = R.string.no_video)
    if (!isLoading && videos.isNotEmpty())
        Column(Modifier.fillMaxSize()) {

            VideosDropDownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                options = videos,
                onItemSelected = { video = it }
            )

            AndroidView(factory = {
                WebView(it).apply {
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            return false
                        }
                    }
                }
            }, update = {
                it.settings.javaScriptEnabled = true
                video?.url?.let { url -> it.loadUrl(url) }
            })
        }
}