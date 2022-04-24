package com.medically.data.repository

import com.medically.core.subjects.SubjectsRepositoryPort
import com.medically.data.InMemoryCache
import com.medically.data.integration.RemoteDataSources
import com.medically.data.remote.SubjectsRemoteDataSource
import com.medically.model.Result
import com.medically.model.Subject

class SubjectsRepositoryImp(
    private val subjectsDataSource: SubjectsRemoteDataSource = RemoteDataSources.subjectsDataSource
) : SubjectsRepositoryPort {
    override suspend fun getAllSubjects(): Result<List<Subject>?> {
        return subjectsDataSource.getAllSubjects()
    }

    override fun saveCurrentSubject(subject: Subject) {
        InMemoryCache.currentSubject = subject
    }

    override fun getCurrentSubject(): Subject? {
        return InMemoryCache.currentSubject
    }
}