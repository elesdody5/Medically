package com.medically.core.current_play

import com.medically.core.entities.BusinessRule
import com.medically.core.persentation.PresentationPort

interface CurrentPlayPort : PresentationPort<CurrentPlayState> {
    val bindCurrentPlay: BusinessRule
}