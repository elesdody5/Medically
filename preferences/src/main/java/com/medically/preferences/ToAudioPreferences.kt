package com.medically.preferences

import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.Lecture
import com.medically.model.Subject


fun List<Lecture>.toPreference(): List<PreferenceLecture> {
    return map {
        PreferenceLecture.newBuilder().setName(it.name)
            .setUrl(it.url ?: "").build()
    }
}

fun Chapter.toPreference(chapter: PreferenceChapter): PreferenceChapter {
    return chapter.toBuilder().setId(id).setDoctorName(doctorName).setImageUrl(imageUrl).build()
}

fun Doctor.toPreference(doctor: PreferenceDoctor): PreferenceDoctor {
    return doctor.toBuilder().setName(name).setSubjectId(subjectId).build()
}

fun Subject.toPreference(subject: PreferenceSubject): PreferenceSubject {
    return subject.toBuilder().setId(id).setName(name).setName(yearName).build()
}