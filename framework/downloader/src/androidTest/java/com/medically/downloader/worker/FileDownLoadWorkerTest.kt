package com.medically.downloader.worker

import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
import com.medically.core.lectures.LecturesRepositoryPort
import com.medically.downloader.data_handler.DownLoadDataHandler
import com.medically.downloader.entities.ChapterParams
import com.medically.downloader.entities.LectureParams
import com.medically.downloader.notification.NotificationHandler
import com.medically.model.Chapter
import com.medically.model.Lecture
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class FileDownLoadWorkerTest {

    private lateinit var applicationContext: Context

    private lateinit var workerFactory: FileDownloadWorkerFactory

    @RelaxedMockK
    private lateinit var notificationManager: NotificationManager

    @RelaxedMockK
    private lateinit var mockLecturesRepositoryPort: LecturesRepositoryPort

    @RelaxedMockK
    private lateinit var notificationHandler: NotificationHandler

    @MockK
    private lateinit var downloadHandler: DownLoadDataHandler

    @Before
    fun init() {
        MockKAnnotations.init(this)
        applicationContext = ApplicationProvider.getApplicationContext()
        workerFactory = FileDownloadWorkerFactory(
            notificationManager,
            notificationHandler,
            downloadHandler,
            mockLecturesRepositoryPort
        )

    }

    @Test
    @Throws(Exception::class)
    fun testDownloadLecture_hasInput_showNotificationAndSaveInRepo() = runTest {

        val lecture = Lecture("lecture", "url")
        val chapter = Chapter("id", "doctor", "chapter", "url")
        // Define input data
        val input = workDataOf(
            LectureParams.KEY_LECTURE_NAME to lecture.name,
            LectureParams.KEY_LECTURE_URL to lecture.url,
            ChapterParams.KEY_Chapter_id to chapter.id,
            ChapterParams.KEY_Chapter_NAME to chapter.name,
        )
        val fileUri = Uri.parse("uri")
        coEvery {
            downloadHandler.downLoadFile(
                applicationContext,
                chapter.name,
                lecture.name!!,
                lecture.url!!
            )
        } returns fileUri

        // Create request
        val worker = TestListenableWorkerBuilder<FileDownLoadWorker>(
            context = applicationContext,
        ).setInputData(input)
            .setWorkerFactory(workerFactory)
            .setInputData(input)
            .build()

        val result = worker.doWork()

        verify {
            notificationHandler.createNotification(
                applicationContext,
                lecture.name!!,
                chapter.name
            )
        }
        coVerify {
            downloadHandler.downLoadFile(
                applicationContext,
                chapter.name,
                lecture.name!!,
                lecture.url!!
            )
        }
        coVerify {
            mockLecturesRepositoryPort.insertLecture(
                any(),
                any()
            )
        }
        assertThat(result, `is`(ListenableWorker.Result.success()))
    }
}