package com.hacksondev.tvmaze_codingchallenge.database

import androidx.room.*

/**
 * Data Access Object for the show table.
 */
@Dao

interface ShowDao {

    @Query("SELECT * FROM databaseshow")
     fun getShows(): List<DatabaseShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(vararg shows: DatabaseShow)
}

@Database(entities = [DatabaseShow::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShowDatabase : RoomDatabase() {
    abstract val showDao: ShowDao
}