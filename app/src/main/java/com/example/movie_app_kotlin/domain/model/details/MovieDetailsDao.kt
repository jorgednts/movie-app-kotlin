package com.example.movie_app_kotlin.domain.model.details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetailsCache: MovieDetailsCache)

    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    suspend fun getMovieDetails(movieId: Int): MovieDetailsCache?
}