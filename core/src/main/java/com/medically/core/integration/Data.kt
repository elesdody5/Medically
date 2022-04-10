package com.medically.core.integration

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.years.YearsRepositoryPort

object Data {
    var yearsRepositoryPort = object : YearsRepositoryPort {}
    var doctorsRepository = object : DoctorsRepositoryPort {}
    var chaptersRepository = object : ChaptersRepositoryPort {}
}