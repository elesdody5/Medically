package com.medically.local.db.dao

import androidx.room.*
import com.medically.local.entities.bookmark.BookmarkedChapter
import com.medically.local.entities.bookmark.BookmarkedLecture
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarksDao {
    @Query("SELECT * FROM bookmark_lecture Where chapter =:chapter AND doctor =:doctor")
    fun getLectures(chapter: String, doctor: String): Flow<List<BookmarkedLecture>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecture(vararg lecture: BookmarkedLecture)

    @Query("SELECT * FROM bookmark_chapter")
    fun getChapters(): Flow<List<BookmarkedChapter>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(vararg chapter: BookmarkedChapter)

    @Query("SELECT EXISTS(SELECT * FROM bookmark_lecture WHERE lecture_url = :url)")
    suspend fun isLectureExists(url: String): Boolean

    @Delete
    suspend fun deleteLecture(vararg lecture: BookmarkedLecture)

    @Delete
    suspend fun deleteChapter(vararg chapter: BookmarkedChapter)
}