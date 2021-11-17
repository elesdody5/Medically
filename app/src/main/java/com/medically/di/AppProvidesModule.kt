package com.medically.di

import android.content.ComponentName
import android.content.Context
import com.medically.audioplayer.MusicServiceConnection
import com.medically.mediaservice.MediaPlaybackService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppProvidesModule {
    @Provides
    fun provideMusicServiceConnection(@ApplicationContext applicationContext: Context): MusicServiceConnection {
        return MusicServiceConnection(
            applicationContext,
            ComponentName(applicationContext, MediaPlaybackService::class.java)
        )
    }
}