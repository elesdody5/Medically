package com.medically.preferences

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

const val PREFERENCES_NAME = "preferences_schema.pb"

object AudioPlayListSerializer : Serializer<PreferenceAudioPlayList> {
    override val defaultValue: PreferenceAudioPlayList =
        PreferenceAudioPlayList.getDefaultInstance()

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun readFrom(input: InputStream): PreferenceAudioPlayList {
        try {
            return PreferenceAudioPlayList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: PreferenceAudioPlayList, output: OutputStream) =
        t.writeTo(output)
}


val Context.audioPreferences: DataStore<PreferenceAudioPlayList> by dataStore(
    fileName = PREFERENCES_NAME,
    serializer = AudioPlayListSerializer
)

