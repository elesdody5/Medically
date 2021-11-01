package com.medically.domain.model


data class Subject(
    val name: String?,
    val doctors: List<String>?
)

data class Doctor(val name: String?)

data class Chapter(val name: String?, val url: String?)

data class Pdf(val name: String?, val url: String?)

data class Video(val name: String?, val url: String?)

data class Lecture(val name: String?, val url: String?)
