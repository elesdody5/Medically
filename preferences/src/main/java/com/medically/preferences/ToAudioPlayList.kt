package com.medically.preferences

import com.medically.model.*

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
    return map { Lecture(it.name, it.url, it.chapter) }
}

fun PreferenceChapter.toChapter(): Chapter {
    return Chapter(id, name, doctorName, imageUrl)
}

fun PreferenceDoctor.toDoctor(): Doctor {
    return Doctor(subjectId, name)
}

fun PreferenceSubject.toSubject(): Subject {
    return Subject(id, name, yearName, icon)
}