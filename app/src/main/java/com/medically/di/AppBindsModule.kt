package com.medically.di

import com.medically.data.remote.RemoteDataSource
import com.medically.data.remote.RemoteDataSourceImp
import com.medically.data.repository.MainRepository
import com.medically.data.repository.Repository
import dagger.Binds

abstract class AppBindsModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun bindRepository(repository: MainRepository): Repository
}