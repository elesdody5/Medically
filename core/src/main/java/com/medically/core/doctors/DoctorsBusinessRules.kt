package com.medically.core.doctors

import com.medically.core.entities.BusinessRules
import com.medically.core.integration.Data

@BusinessRules
suspend fun DoctorsPort.bindDoctors() {
    doctors.value = Data.doctorsRepository.getDoctors()
}