package com.medically.core.subjects

import com.medically.model.Subject

interface SubjectPort {
    val subject: Subject
    val subjects: List<Subject>
}