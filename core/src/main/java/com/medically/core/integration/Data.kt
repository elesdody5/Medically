package com.medically.core.integration

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.years.YearsRepositoryPort

object Data {
    lateinit var yearsRepositoryPort: YearsRepositoryPort
    lateinit var doctorsRepository: DoctorsRepositoryPort
    lateinit var chaptersRepository: ChaptersRepositoryPort
}