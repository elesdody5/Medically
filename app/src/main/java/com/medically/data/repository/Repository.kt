package com.medically.data.repository

import com.medically.data.entity.Subject

interface Repository {
    fun getSubjectsByYear(year: String): Result<List<Subject>>
}