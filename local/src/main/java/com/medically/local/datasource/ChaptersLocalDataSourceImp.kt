package com.medically.local.datasource

import com.medically.data.local.ChaptersLocalDataSource
import com.medically.local.db.dao.BookmarksDao
import com.medically.local.db.dao.OfflineDao
import com.medically.local.db.dao.ProgressDao
import com.medically.local.entities.bookmark.toBookmarkedChapter
import com.medically.local.entities.bookmark.toChapters
import com.medically.local.entities.offline.toChapters
import com.medically.local.entities.offline.toOfflineChapters
import com.medically.local.entities.progress.toChapterProgress
import com.medically.local.entities.progress.toChapterProgressEntity
import com.medically.model.Chapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChaptersLocalDataSourceImp(
    private val offlineDao: OfflineDao,
    private val bookmarksDao: BookmarksDao,
    private val progressDao: ProgressDao
) : ChaptersLocalDataSource {

    override fun getOfflineChapters(): Flow<List<Chapter>> {
        return offlineDao.getChapters().map { it.toChapters() }
    }

    override fun getBookmarksChapters(): Flow<List<Chapter>> {
        return bookmarksDao.getChapters().map { it.toChapters() }
    }

    override fun getChaptersProgress(): Flow<List<Chapter>> {
        return progressDao.getChapters().map { it.toChapterProgress() }
    }

    override suspend fun insertChapterProgress(
        chapterProgress: Chapter,
    ) {
        progressDao.insertChapter(chapterProgress.toChapterProgressEntity())
    }

    override suspend fun removeBookmarkChapter(vararg chapter: Chapter) {
        bookmarksDao.deleteChapter(*chapter.toBookmarkedChapter())
    }

    override suspend fun removeOfflineChapters(vararg chapter: Chapter) {
        offlineDao.deleteChapters(*chapter.toOfflineChapters())
    }
}