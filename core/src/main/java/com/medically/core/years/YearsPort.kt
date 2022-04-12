package com.medically.core.years


import com.medically.core.entities.BusinessRule
import com.medically.model.Year
import kotlinx.coroutines.flow.MutableStateFlow

interface YearsPort {
    var years: List<Year>
    val filteredYears: MutableStateFlow<List<Year>>
    val bindYears: BusinessRule
}