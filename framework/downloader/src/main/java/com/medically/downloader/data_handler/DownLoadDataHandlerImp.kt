package com.medically.downloader.data_handler

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

class DownLoadDataHandlerImp : DownLoadDataHandler {

    override suspend fun downLoadFile(
        appContext: Context,
        dir: String,
        fileName: String,
        url: String
    ): Uri? {
        runCatching {
            withContext(Dispatchers.IO) {
                val file = createLocalFile(appContext, dir, fileName, url)
                URL(url).openStream().use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
                file
            }
        }.onSuccess {
            return it?.toUri()
        }
        return null
    }

    private fun createLocalFile(
        appContext: Context,
        dir: String,
        fileName: String,
        url: String
    ): File? {
        // create dir to hold downloaded lectures in same doctor
        val dir: File = appContext.getDir(dir, Context.MODE_PRIVATE)
        // create local file to hold downloaded lecture
        var localFile: File? = null
        try {
            // to get the file extension from storage
            val dotIndex: Int = url.lastIndexOf('.')
            val extension: String = url.substring(dotIndex)
            localFile = File.createTempFile(fileName, extension, dir)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return localFile
    }

}