package com.medically.core.integration

import com.medically.core.chapters.ChaptersRepositoryPort
import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.years.YearsRepositoryPort
import com.medically.core.tracking.Logger


@DslMarker
private annotation class CoreIntegration

@CoreIntegration
fun coreIntegration(integrator: CoreIntegrator.() -> Unit) {
    CoreIntegrator.apply(integrator)
}

object CoreIntegrator {
    @CoreIntegration
    val with = this


    @CoreIntegration
    infix fun yearsRepository(repository: YearsRepositoryPort) {
        Data.yearsRepositoryPort = repository
    }

    @CoreIntegration
    infix fun doctorsRepository(repository: DoctorsRepositoryPort) {
        Data.doctorsRepository = repository
    }

    @CoreIntegration
    infix fun chaptersRepository(repository: ChaptersRepositoryPort) {
        Data.chaptersRepository = repository
    }

    @CoreIntegration
    infix fun appLogger(logger: Logger) {
        Tracking.logger = logger
    }
}
