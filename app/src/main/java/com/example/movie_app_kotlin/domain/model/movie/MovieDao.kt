package com.example.movie_app_kotlin.domain.model.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieCache>)

    @Query("SELECT * FROM movies")
    suspend fun getMovieList(): List<MovieCache>?
}