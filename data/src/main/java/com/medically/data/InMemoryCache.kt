package com.medically.data

import com.medically.model.Chapter
import com.medically.model.Subject


internal object InMemoryCache {
    var currentYear: String? = null
    var currentSubject: Subject? = null
    var currentChapter: Chapter? = null
}
