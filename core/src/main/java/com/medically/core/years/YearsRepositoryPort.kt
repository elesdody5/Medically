package com.medically.core.years

import com.medically.core.entities.Year

interface YearsRepositoryPort {
    suspend fun getAllYears(): List<Year>
}