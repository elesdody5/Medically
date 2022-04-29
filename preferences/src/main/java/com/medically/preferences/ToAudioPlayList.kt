package com.medically.preferences

import com.medically.model.*

fun PreferenceAudioPlayList.toAudioPlayList(): AudioPlayList {
    return AudioPlayList(
        lectures = lecturesList.toLecture(),
        currentPlayingPosition,
        chapter.toChapter(),
        doctor.toDoctor(),
        subject.toSubject()
    )
}

fun List<PreferenceLecture>.toLecture(): List<Lecture> {
    return map { Lecture(it.number, it.name, it.url) }
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