package com.medically.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medically.core.current_play.bindCurrentPlay
import com.medically.core.entities.BusinessRule
import com.medically.core.subjects.SubjectsPort
import com.medically.core.subjects.SubjectsPortState
import com.medically.core.subjects.bindSubjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel(), SubjectsPort {
    override val state = MutableStateFlow(SubjectsPortState())
    override val scope: CoroutineScope = viewModelScope
    override val bindCurrentPlay = bindCurrentPlay()
    override val bindSubjects: BusinessRule = bindSubjects()
}