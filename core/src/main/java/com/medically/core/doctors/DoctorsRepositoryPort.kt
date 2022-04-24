package com.medically.core.doctors

import com.medically.core.entities.BusinessRule
import com.medically.model.Doctor
import com.medically.model.Result
import com.medically.model.Subject

interface DoctorsRepositoryPort {
    suspend fun getDoctors(): Result<List<Doctor>>

}