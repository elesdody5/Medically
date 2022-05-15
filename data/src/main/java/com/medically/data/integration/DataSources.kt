package com.medically.data.integration

import com.medically.data.local.ChaptersLocalDataSource
import com.medically.data.local.LecturesLocalDataSource
import com.medically.data.preferences.PreferencesManager
import com.medically.data.remote.*

object RemoteDataSources {
    lateinit var subjectsDataSource: SubjectsRemoteDataSource
    lateinit var doctorsRemoteDataSource: DoctorsRemoteDataSource
    lateinit var subjectDetailsRemoteDataSource: SubjectDetailsRemoteDataSource
    lateinit var chaptersRemoteDataSource: ChaptersRemoteDataSource
    lateinit var lecturesRemoteDataSource: LecturesRemoteDataSource
}

object LocalDataSources {
    lateinit var lecturesLocalDataSource: LecturesLocalDataSource
    lateinit var chaptersLocalDatasource: ChaptersLocalDataSource
}

object PreferencesDataSources {
    lateinit var preferencesManager: PreferencesManager
}