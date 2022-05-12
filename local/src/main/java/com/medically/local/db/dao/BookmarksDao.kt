package com.medically.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.medically.local.entities.bookmark.BookmarkedChapter
import com.medically.local.entities.bookmark.BookmarkedLecture
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarksDao {
    @Query("SELECT * FROM bookmark_lecture Where chapter =:chapter")
    fun getLectures(chapter: String): Flow<List<BookmarkedLecture>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecture(vararg lecture: BookmarkedLecture)

    @Query("SELECT * FROM bookmark_chapter")
    fun getChapters(): Flow<List<BookmarkedChapter>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(vararg chapter: BookmarkedChapter)
}