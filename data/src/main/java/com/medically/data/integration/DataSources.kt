package com.medically.data.integration

import com.medically.data.remote.DoctorsRemoteDataSource
import com.medically.data.remote.SubjectDetailsRemoteDataSource
import com.medically.data.remote.SubjectsRemoteDataSource

object RemoteDataSources {
    lateinit var subjectsDataSource: SubjectsRemoteDataSource
    lateinit var doctorsRemoteDataSource: DoctorsRemoteDataSource
    lateinit var subjectDetailsRemoteDataSource: SubjectDetailsRemoteDataSource

}

object LocalDataSources