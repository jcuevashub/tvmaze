package com.hacksondev.tvmaze_codingchallenge.database

import androidx.room.*

@Dao
interface ShowDao {

    @Query("SELECT * FROM databaseshow")
    fun getShows(): List<DatabaseShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg shows: DatabaseShow)
}

@Database(entities = [DatabaseShow::class], version = 1, exportSchema = false)
abstract class ShowDatabase : RoomDatabase() {
    abstract val showDao: ShowDao
}