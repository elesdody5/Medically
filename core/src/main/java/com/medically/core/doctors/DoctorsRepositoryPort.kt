package com.medically.core.doctors

import com.medically.model.Doctor
import com.medically.model.Result

interface DoctorsRepositoryPort {
    suspend fun getDoctors(): Result<List<Doctor>>
    fun saveCurrentDoctor(doctor: Doctor)
    val currentDoctor: Doctor?
}