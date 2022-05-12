package com.medically.downloader

import android.net.Uri
import androidx.work.Data
import com.medically.downloader.entities.ChapterParams
import com.medically.downloader.entities.DoctorParams
import com.medically.downloader.entities.LectureParams
import com.medically.model.Chapter
import com.medically.model.Doctor
import com.medically.model.Lecture

fun String.toUri(): Uri {
    return Uri.parse(this)
}

fun Data.getLecture(): Lecture {
    val name = getString(LectureParams.KEY_LECTURE_NAME) ?: ""
    val url = getString(LectureParams.KEY_LECTURE_URL) ?: ""
    val chapter = getString(LectureParams.KEY_LECTURE_CHAPTER) ?: ""
    return Lecture(name, url, chapter)
}

fun Data.getChapter(): Chapter {
    val id = getString(ChapterParams.KEY_Chapter_id)
    val doctor = getString(ChapterParams.KEY_Doctor)
    val name = getString(ChapterParams.KEY_Chapter_NAME) ?: ""
    val image = getString(ChapterParams.KEY_Chapter_IMAGE)
    return Chapter(name = name, imageUrl = image ?: "", doctorName = doctor ?: "")
}

fun Data.getDoctor(): Doctor {
    val name = getString(DoctorParams.KEY_DOCTOR_NAME)
    val subject = getString(DoctorParams.KEY_Subject)
    return Doctor(name, subject)
}