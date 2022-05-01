package com.medically.core.integration

import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.core.subject_details.SubjectDetailsRepositoryPort
import com.medically.core.subjects.SubjectsRepositoryPort

object Data {
    lateinit var lecturesRepository: LecturesRepositoryPort
    lateinit var subjectsRepositoryPort: SubjectsRepositoryPort
    lateinit var doctorsRepository: DoctorsRepositoryPort
    lateinit var subjectDetailsRepository: SubjectDetailsRepositoryPort
}