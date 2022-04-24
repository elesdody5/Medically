package com.medically.data

import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.Subject


internal object InMemoryCache {
    var currentSubject: Subject? = null
    var currentChapter: Chapter? = null
    var currentDoctor: Doctor? = null
}
