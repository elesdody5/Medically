package com.medically.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.medically.local.entities.offline.OfflineChapter
import com.medically.local.entities.offline.OfflineLecture

@Dao
interface LecturesDao {

    @Query("SELECT * FROM lecturesEntry Where chapter =:chapter")
    fun getLectures(chapter: String): List<OfflineLecture>

    @Query("SELECT * FROM chaptersEntry")
    fun getChapters(): List<OfflineChapter>

    @Insert
    fun insertLecture(vararg lecture: OfflineLecture)


    @Insert
    fun insertChapter(vararg chapter: OfflineChapter)
}