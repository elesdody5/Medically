package com.medically.core.integration

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.subjects.SubjectsRepositoryPort

object Data {
    lateinit var subjectsRepositoryPort: SubjectsRepositoryPort
    lateinit var doctorsRepository: DoctorsRepositoryPort
    lateinit var chaptersRepository: ChaptersRepositoryPort
}