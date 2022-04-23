package com.medically

import android.app.Application
import com.medically.core.integration.coreIntegration
import com.medically.data.integration.dataIntegrator
import com.medically.data.repository.DoctorsRepositoryImp
import com.medically.data.repository.SubjectDetailsRepositoryImp
import com.medically.data.repository.SubjectsRepositoryImp
import com.medically.remote.data_source.DoctorsRemoteDataSourceImp
import com.medically.remote.data_source.SubjectDetailsRemoteDataSourceImp
import com.medically.remote.data_source.SubjectsRemoteDataSourceImp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MedicallyApplication : Application(

) {
    override fun onCreate() {
        super.onCreate()

        dataIntegrator {
            with subjectsRemoteDataSource SubjectsRemoteDataSourceImp()
            with doctorsRemoteDataSource DoctorsRemoteDataSourceImp()
            with chaptersRemoteDataSource SubjectDetailsRemoteDataSourceImp()
        }

        coreIntegration {
            with subjectsRepository SubjectsRepositoryImp()
            with doctorsRepository DoctorsRepositoryImp()
            with subjectDetailsRepository SubjectDetailsRepositoryImp()
        }

    }
}