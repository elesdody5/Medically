package com.medically.preferences

import com.medically.model.AudioPlayList
import com.medically.model.Chapter
import com.medically.model.Lecture

fun PreferenceAudioPlayList.toAudioPlayList(): AudioPlayList {
    return AudioPlayList(
        lectures = lecturesList.toLecture(),
        currentPlayingPosition,
        chapter.toChapter(),
        doctorName,
        subjectTitle
    )
}

fun List<PreferenceLecture>.toLecture(): List<Lecture> {
    return map { Lecture(it.name, it.url, it.chapter, it.doctor) }
}

fun PreferenceChapter.toChapter(): Chapter {
    return Chapter(doctorName, name, imageUrl)
}


