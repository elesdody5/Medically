package com.medically.local.db.dao

import androidx.room.*
import com.medically.local.entities.offline.OfflineChapter
import com.medically.local.entities.offline.OfflineLecture
import kotlinx.coroutines.flow.Flow

@Dao
interface OfflineDao {

    @Query("SELECT * FROM lecturesEntry Where chapter =:chapter AND doctor =:doctor")
    fun getLectures(chapter: String, doctor: String): Flow<List<OfflineLecture>>

    @Query("SELECT * FROM chaptersEntry")
    fun getChapters(): Flow<List<OfflineChapter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecture(vararg lecture: OfflineLecture)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(vararg chapter: OfflineChapter)

    @Delete
    suspend fun deleteChapters(vararg chapter: OfflineChapter)

    @Query("DELETE FROM lecturesEntry WHERE chapter=:chapter AND doctor=:doctor")
    suspend fun deleteLectures(chapter: String, doctor: String)
}