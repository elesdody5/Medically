package com.medically.core.downloader

import com.medically.model.Chapter
import com.medically.model.Lecture

interface DownLoaderPort {
    fun downLoad(lecture: Lecture, chapter: Chapter)
}