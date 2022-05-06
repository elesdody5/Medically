package com.medically.local.datasource

import com.medically.data.local.LecturesLocalDataSource
import com.medically.local.db.LecturesDao
import com.medically.local.entities.offline.toLecture
import com.medically.local.entities.offline.toLocalLecture
import com.medically.local.entities.offline.toOfflineChapter
import com.medically.model.Chapter
import com.medically.model.Lecture

class LecturesLocalDataSourceImp(private val dao: LecturesDao) :
    LecturesLocalDataSource {
    override suspend fun insertLecture(
        chapter: Chapter,
        vararg lecture: Lecture
    ) {
        dao.insertChapter(chapter.toOfflineChapter())
        dao.insertLecture(*lecture.toLocalLecture())
    }

    override suspend fun getLectures(chapter: String): List<Lecture> {
        return dao.getLectures(chapter).toLecture()
    }
}