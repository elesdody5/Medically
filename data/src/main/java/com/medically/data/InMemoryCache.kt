package com.medically.data

import com.medically.model.AudioPlayList
import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.Subject
import kotlinx.coroutines.flow.MutableStateFlow


internal object InMemoryCache {

    val currentPlayList = MutableStateFlow(AudioPlayList())
    var currentSubject: Subject? = null
    var currentChapter: Chapter? = null
    var currentDoctor: Doctor? = null
}
