package com.medically.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.medically.data.preferences.PreferencesManager
import com.medically.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val TEST_DATASTORE_NAME: String = "test_datastore"

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PreferencesImpTest {

    private val testContext: Context = ApplicationProvider.getApplicationContext()
    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val testCoroutineScope = TestScope(testCoroutineDispatcher + Job())

    private val testDataStore: DataStore<PreferenceAudioPlayList> =
        DataStoreFactory.create(
            scope = testCoroutineScope,
            serializer = AudioPlayListSerializer,
            produceFile = { testContext.dataStoreFile(TEST_DATASTORE_NAME) }
        )
    private lateinit var preferenceManager: PreferencesManager

    @Before
    fun setup() {
        preferenceManager = PreferencesImp(testDataStore)
    }

    @Test
    fun getCurrentAudioPlayList_preferencesHasData_AudioPlayList() = runTest {
        //Given audio play list
        val audioPlayList = AudioPlayList(
            chapter = Chapter("id", "doctor", "chapter", "image"),
            doctor = Doctor("subject", "doctor"),
            subject = Subject("id", "subject", "year"),
            lectures = listOf(Lecture("1", "lecture", "url")),
            currentPlayingPosition = 0
        )

        // When preferences has data
        preferenceManager.setCurrentAudioPlayList(audioPlayList)
        var currentPlayList: AudioPlayList? = null
        preferenceManager.currentAudioPlayList.take(1).collect { currentPlayList = it }
        //Then preferencesManger return current play list
        assertThat(currentPlayList?.chapter?.id, `is`(audioPlayList.chapter?.id))
        assertThat(currentPlayList?.doctor?.name, `is`(audioPlayList.doctor?.name))
        assertThat(currentPlayList?.subject?.id, `is`(audioPlayList.subject?.id))
        assertThat(
            currentPlayList?.lectures?.firstOrNull()?.name,
            `is`(audioPlayList.lectures?.firstOrNull()?.name)
        )
        assertThat(
            currentPlayList?.currentPlayingPosition,
            `is`(audioPlayList.currentPlayingPosition)
        )
    }

}