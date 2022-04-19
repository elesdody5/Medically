package com.medically

import android.app.Application
import com.medically.core.integration.coreIntegration
import com.medically.data.integration.dataIntegrator
import com.medically.data.repository.SubjectsRepositoryImp
import com.medically.remote.data_source.SubjectsRemoteDataSourceImp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedicallyApplication : Application(

) {
    override fun onCreate() {
        super.onCreate()

        dataIntegrator {
            with subjectsRemoteDataSource SubjectsRemoteDataSourceImp()

        }

        coreIntegration {
            with subjectsRepository SubjectsRepositoryImp()
        }

    }
}