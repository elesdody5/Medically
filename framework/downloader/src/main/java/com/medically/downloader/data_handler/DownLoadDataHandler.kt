package com.medically.downloader.data_handler

import android.content.Context
import android.net.Uri

interface DownLoadDataHandler {
    suspend fun downLoadFile(appContext: Context, dir: String, fileName: String, url: String): Uri?
}