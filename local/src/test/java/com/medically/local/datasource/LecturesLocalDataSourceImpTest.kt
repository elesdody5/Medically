package com.medically.local.datasource

import com.medically.data.local.LecturesLocalDataSource
import com.medically.local.db.dao.BookmarksDao
import com.medically.local.db.dao.OfflineDao
import com.medically.local.db.dao.ProgressDao
import com.medically.local.entities.progress.ChapterProgressQuery
import com.medically.model.Chapter
import com.medically.model.Lecture
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LecturesLocalDataSourceImpTest {

    @MockK
    lateinit var progressDao: ProgressDao

    @MockK
    lateinit var offlineDao: OfflineDao

    @MockK
    lateinit var bookmarksDao: BookmarksDao

    lateinit var lecturesLocalDataSource: LecturesLocalDataSource

    @Before
    fun init() {
        MockKAnnotations.init(this)
        lecturesLocalDataSource = LecturesLocalDataSourceImp(
            progressDao = progressDao,
            bookmarksDao = bookmarksDao,
            offlineDao = offlineDao
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun completeLecture() = runTest {
        val lectureProgress = Lecture("lecture", chapterName = "chapter", url = "", doctor = "")
        val chapterProgress = Chapter(
            name = "chapter",
            doctorName = "doctor",
            imageUrl = "",
            progress = 10,
            lecturesCount = 50
        )
        val progress = (chapterProgress.progress.toDouble() / 50) * 100
        coEvery {
            progressDao.getCompletedLecturesCount(
                true,
                "chapter"
            )
        } returns chapterProgress.progress
        coEvery { progressDao.getLecturesCount("chapter") } returns chapterProgress.lecturesCount
        coEvery { progressDao.insertLecture(any()) } just Runs
        coEvery { progressDao.updateChapterProgress(any()) } just Runs

        lecturesLocalDataSource.completeLecture(chapterProgress, lectureProgress)

        coVerify {
            progressDao.updateChapterProgress(
                ChapterProgressQuery(
                    progress.toInt(),
                    chapterProgress.name,
                    chapterProgress.doctorName
                )
            )
        }
    }
}