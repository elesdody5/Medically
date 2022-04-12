package com.medically.core.doctors

import com.medically.core.entities.BusinessRule
import com.medically.model.Doctor

interface DoctorsRepositoryPort {
    suspend fun getDoctors(): List<Doctor>

}