package com.medically.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.medically.local.entities.bookmark.BookmarkedChapter
import com.medically.local.entities.bookmark.BookmarkedLecture
import com.medically.local.entities.offline.OfflineChapter
import com.medically.local.entities.offline.OfflineLecture

private const val DB_VERSION = 2
private const val DATABASE_NAME = "offline_data"

@Database(
    version = DB_VERSION,
    entities = [OfflineLecture::class, OfflineChapter::class, BookmarkedChapter::class, BookmarkedLecture::class],
    exportSchema = false
)
abstract class MedicallyDatabase : RoomDatabase() {
    abstract fun offlineDao(): OfflineDao
    abstract fun bookmarksDao(): BookmarksDao

    /**
     * Define a companion object, this allows us to add functions on the MedicallyDatabase class.
     */
    companion object {
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */
        @Volatile
        private var INSTANCE: MedicallyDatabase? = null

        /**
         * Helper function to get the database.
         *
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         *
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         *
         * @param context The application context Singleton, used to get access to the filesystem.
         */
        fun getInstance(context: Context): MedicallyDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MedicallyDatabase::class.java,
                        DATABASE_NAME
                    )
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}
