package com.medically.local.db.dao

import androidx.room.*
import com.medically.local.entities.progress.ChapterProgressEntity
import com.medically.local.entities.progress.ChapterProgressQuery
import com.medically.local.entities.progress.LectureProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM lecture_progress Where chapter =:chapter AND doctor =:doctor")
    fun getLectures(chapter: String, doctor: String): Flow<List<LectureProgressEntity>>

    @Query("SELECT COUNT(*) FROM lecture_progress Where chapter =:chapter AND completed =:isCompleted")
    suspend fun getCompletedLecturesCount(isCompleted: Boolean, chapter: String): Int

    @Query("SELECT lectures_count FROM chapter_progress where name =:chapter")
    suspend fun getLecturesCount(chapter: String): Int


    @Query("SELECT * FROM chapter_progress")
    fun getChapters(): Flow<List<ChapterProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecture(vararg lecture: LectureProgressEntity)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChapter(vararg chapter: ChapterProgressEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = ChapterProgressEntity::class)
    suspend fun updateChapterProgress(chapterProgress: ChapterProgressQuery)
}