package com.medically.core.years

import com.medically.model.Year
import com.medically.model.Result

interface YearsRepositoryPort {
    suspend fun getAllYears(): Result<List<Year>?>
}