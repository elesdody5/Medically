package com.medically.local.datasource

import com.medically.data.local.LecturesLocalDataSource
import com.medically.local.db.OfflineDao
import com.medically.local.entities.offline.toLecture
import com.medically.local.entities.offline.toLocalLecture
import com.medically.local.entities.offline.toOfflineChapter
import com.medically.model.Chapter
import com.medically.model.Lecture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LecturesLocalDataSourceImp(private val dao: OfflineDao) :
    LecturesLocalDataSource {
    override suspend fun insertLecture(
        chapter: Chapter,
        vararg lecture: Lecture
    ) {
        dao.insertChapter(chapter.toOfflineChapter())
        dao.insertLecture(*lecture.toLocalLecture())
    }

    override fun getLectures(chapter: String): Flow<List<Lecture>> {
        return dao.getLectures(chapter).map { it.toLecture() }
    }
}