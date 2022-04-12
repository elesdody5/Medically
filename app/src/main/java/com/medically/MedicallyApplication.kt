package com.medically

import android.app.Application
import com.medically.core.integration.coreIntegration
import com.medically.data.integration.dataIntegrator
import com.medically.data.remote.RemoteDataSourceImp
import com.medically.data.remote.YearsRemoteDataSource
import com.medically.data.repository.YearsRepositoryImp
import com.medically.remote.data_source.YearsRemoteDataSourceImp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedicallyApplication : Application(

) {
    override fun onCreate() {
        super.onCreate()

        dataIntegrator {
            with yearsRemoteDataSource YearsRemoteDataSourceImp()
        }

        coreIntegration {
            with yearsRepository YearsRepositoryImp()
        }

    }
}