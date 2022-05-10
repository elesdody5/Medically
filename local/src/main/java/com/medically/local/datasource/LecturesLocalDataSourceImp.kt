package com.medically.local.datasource

import com.medically.data.local.LecturesLocalDataSource
import com.medically.local.db.BookmarksDao
import com.medically.local.db.OfflineDao
import com.medically.local.entities.bookmark.toBookmark
import com.medically.local.entities.bookmark.toBookmarkedLecture
import com.medically.local.entities.offline.toLecture
import com.medically.local.entities.offline.toLocalLecture
import com.medically.local.entities.offline.toOfflineChapter
import com.medically.model.Chapter
import com.medically.model.Lecture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LecturesLocalDataSourceImp(
    private val dao: OfflineDao,
    private val bookmarksDao: BookmarksDao
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
}