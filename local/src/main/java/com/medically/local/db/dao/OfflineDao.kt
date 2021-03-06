package com.medically.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.medically.local.entities.offline.OfflineChapter
import com.medically.local.entities.offline.OfflineLecture
import kotlinx.coroutines.flow.Flow

@Dao
interface OfflineDao {

    @Query("SELECT * FROM lecturesEntry Where chapter =:chapter")
    fun getLectures(chapter: String): Flow<List<OfflineLecture>>

    @Query("SELECT * FROM chaptersEntry")
    fun getChapters(): Flow<List<OfflineChapter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecture(vararg lecture: OfflineLecture)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(vararg chapter: OfflineChapter)
}