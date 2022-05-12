package com.medically.local.datasource

import com.medically.data.local.LecturesLocalDataSource
import com.medically.local.db.dao.BookmarksDao
import com.medically.local.db.dao.OfflineDao
import com.medically.local.db.dao.ProgressDao
import com.medically.local.entities.bookmark.toBookmark
import com.medically.local.entities.bookmark.toBookmarkedLecture
import com.medically.local.entities.offline.toLecture
import com.medically.local.entities.offline.toLocalLecture
import com.medically.local.entities.offline.toOfflineChapter
import com.medically.local.entities.progress.ChapterProgressQuery
import com.medically.local.entities.progress.toCompleted
import com.medically.local.entities.progress.toLectureProgress
import com.medically.model.Chapter
import com.medically.model.Lecture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LecturesLocalDataSourceImp(
    private val dao: OfflineDao,
    private val bookmarksDao: BookmarksDao,
    private val progressDao: ProgressDao
) :
    LecturesLocalDataSource {
    override suspend fun insertLecture(
        chapter: Chapter,
        vararg lecture: Lecture
    ) {
        dao.insertChapter(chapter.toOfflineChapter())
        dao.insertLecture(*lecture.toLocalLecture())
    }

    override fun getOfflineLectures(chapter: String): Flow<List<Lecture>> {
        return dao.getLectures(chapter).map { it.toLecture() }
    }

    override suspend fun insertBookmarkLectures(chapter: Chapter, vararg lecture: Lecture) {
        bookmarksDao.insertChapter(chapter.toBookmark())
        bookmarksDao.insertLecture(*lecture.toBookmarkedLecture())
    }

    override fun getBookmarkedLectures(chapter: String): Flow<List<Lecture>> {
        return dao.getLectures(chapter).map { it.toLecture() }
    }

    override suspend fun completeLecture(chapter: Chapter, lecture: Lecture) {
        lecture.isCompleted = true
        progressDao.insertLecture(lecture.toCompleted())
        val lecturesCount = progressDao.getLecturesCount(chapter.name)
        val completedLecture = progressDao.getCompletedLecturesCount(true, lecture.chapterName)
        val progress = (completedLecture.toDouble() / lecturesCount) * 100
        progressDao.updateChapterProgress(
            ChapterProgressQuery(
                progress.toInt(),
                name = chapter.name,
                doctorName = chapter.doctorName
            )
        )
    }

    override suspend fun getCompletedLectures(chapter: Chapter): Flow<List<Lecture>> {
        return progressDao.getLectures(chapter.name).map { it.toLectureProgress() }
    }

    override suspend fun isLectureBookmarked(url: String): Boolean {
        return bookmarksDao.isLectureExists(url)
    }

    override suspend fun removeBookmark(vararg lecture: Lecture) {
        bookmarksDao.deleteLecture(*lecture.toBookmarkedLecture())
    }
}