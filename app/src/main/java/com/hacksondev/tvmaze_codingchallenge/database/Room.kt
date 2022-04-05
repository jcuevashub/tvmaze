package com.hacksondev.tvmaze_codingchallenge.database

import android.content.Context
import androidx.room.*
import com.hacksondev.tvmaze_codingchallenge.domain.Show

@Database(entities = [DatabaseShow::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShowDatabase : RoomDatabase() {
    abstract val showDao: ShowDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ShowDatabase? = null

        fun getDatabase(context: Context): ShowDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowDatabase::class.java,
                    "show_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

