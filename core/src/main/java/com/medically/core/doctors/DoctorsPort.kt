package com.medically.core.doctors

import com.medically.core.entities.BusinessRule
import com.medically.core.entities.Doctor
import kotlinx.coroutines.flow.MutableStateFlow

interface DoctorsPort {
    val doctors: MutableStateFlow<List<Doctor>>
    val bindDoctors: BusinessRule
}