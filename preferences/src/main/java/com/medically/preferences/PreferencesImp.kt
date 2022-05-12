package com.medically.preferences

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import com.medically.data.preferences.PreferencesManager
import com.medically.model.AudioPlayList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesImp(
    private val context: Application,
    private val dataStore: DataStore<PreferenceAudioPlayList> = context.audioPreferences,
) : PreferencesManager {
    override val currentAudioPlayList: Flow<AudioPlayList> = dataStore.data
        .map { it.toAudioPlayList() }
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("PreferencesManager", "Error reading sort order preferences.", exception)
                emit(PreferenceAudioPlayList.getDefaultInstance().toAudioPlayList())
            } else {
                throw exception
            }
        }

    override suspend fun setCurrentAudioPlayList(audioPlayList: AudioPlayList) {
        dataStore.updateData {
            it.toBuilder()
                .setChapter(audioPlayList.chapter?.toPreference(it.chapter))
                .setDoctorName(audioPlayList.doctorName)
                .setSubjectTitle(audioPlayList.subjectTitle)
                .clearLectures()
                .addAllLectures(audioPlayList.lectures?.toPreference())
                .setCurrentPlayingPosition(audioPlayList.currentPlayingPosition ?: 0)
                .build()
        }
    }

}
