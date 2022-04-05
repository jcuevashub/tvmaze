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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(show: DatabaseShow)

    @Query("SELECT EXISTS (SELECT 1 FROM databaseshow WHERE id=:id)")
    fun isFavorite(id: Int): Int

    @Delete
    fun delete(shows: DatabaseShow)
}