package com.medically.data.integration

import com.medically.data.remote.ChaptersRemoteDataSource
import com.medically.data.remote.DoctorsRemoteDataSource
import com.medically.data.remote.SubjectsRemoteDataSource

object RemoteDataSources {
    lateinit var subjectsDataSource: SubjectsRemoteDataSource
    lateinit var doctorsRemoteDataSource: DoctorsRemoteDataSource
    lateinit var chaptersRemoteDataSource: ChaptersRemoteDataSource

}

object LocalDataSources {

}