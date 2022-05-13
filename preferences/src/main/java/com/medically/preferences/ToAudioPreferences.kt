package com.medically.preferences

import com.medically.model.Chapter
import com.medically.model.Lecture


fun List<Lecture>.toPreference(): List<PreferenceLecture> {
    return map {
        PreferenceLecture.newBuilder().setName(it.name)
            .setUrl(it.url).build()
    }
}

fun Chapter.toPreference(chapter: PreferenceChapter): PreferenceChapter {
    return chapter.toBuilder().setDoctorName(doctorName).setImageUrl(imageUrl).build()
}

