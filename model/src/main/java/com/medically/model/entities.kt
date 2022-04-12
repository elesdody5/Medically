package com.medically.model

abstract class Subject {
    abstract val id: String
    abstract val name: String
}

abstract class Chapter {
    abstract val id: Long?
    abstract val doctorName: String?
    abstract val name: String
    abstract val imageUrl: String
}

abstract class Year {
    abstract val title: String
    abstract val subjects: List<Subject>
}

abstract class Doctor {
    abstract val name: String?
}

abstract class Pdf {
    abstract val name: String?
    abstract val url: String?
}

abstract class Video {
    abstract val name: String?
    abstract val url: String?
}

abstract class Lecture {
    abstract val number: Int?
    abstract val name: String?
    abstract val url: String?
    abstract val size: Long?
}

abstract class AudioPlayList {
    abstract val lecture: List<Lecture>
    abstract val lecturePosition: Int
    abstract val chapter: Chapter
    abstract val doctor: Doctor
    abstract val subject: Subject
} 