package com.medically.data

import com.medically.model.*
import kotlinx.coroutines.flow.MutableStateFlow


internal object InMemoryCache {

    val currentPlayList = MutableStateFlow(AudioPlayList())
    var currentDownloadedList = emptyList<Lecture>()
    var currentSubject: Subject? = null
    var currentChapter: Chapter? = null
    var currentDoctor: Doctor? = null
}
