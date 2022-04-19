package com.medically.core.persentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow


interface PresentationPort<T : PresentationPortState> {
    val scope: CoroutineScope
    val state: MutableStateFlow<T>
}

abstract class PresentationPortState(open val isLoading: Boolean, open val errorMessage: String?)