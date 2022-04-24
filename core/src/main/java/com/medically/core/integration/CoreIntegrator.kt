package com.medically.core.integration

import com.medically.core.doctors.DoctorsRepositoryPort
import com.medically.core.subject_details.SubjectDetailsRepositoryPort
import com.medically.core.subjects.SubjectsRepositoryPort
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
    infix fun subjectsRepository(repository: SubjectsRepositoryPort) {
        Data.subjectsRepositoryPort = repository
    }

    @CoreIntegration
    infix fun doctorsRepository(repository: DoctorsRepositoryPort) {
        Data.doctorsRepository = repository
    }

    @CoreIntegration
    infix fun subjectDetailsRepository(repository: SubjectDetailsRepositoryPort) {
        Data.subjectDetailsRepository = repository
    }

    @CoreIntegration
    infix fun appLogger(logger: Logger) {
        Tracking.logger = logger
    }
}
