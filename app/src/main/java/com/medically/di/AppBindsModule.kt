package com.medically.di

import com.medically.data.remote.RemoteDataSource
import com.medically.data.remote.RemoteDataSourceImp
import com.medically.data.remote.network.FirebaseImp
import com.medically.data.remote.network.NetworkServices
import com.medically.data.repository.MainRepository
import com.medically.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun bindFirebaseImp(firebaseImp: FirebaseImp): NetworkServices

    @Binds
    abstract fun bindRepository(repository: MainRepository): Repository
}