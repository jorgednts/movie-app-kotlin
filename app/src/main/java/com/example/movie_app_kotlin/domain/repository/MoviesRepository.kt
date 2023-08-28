package com.example.movie_app_kotlin.domain.repository

import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel

interface MoviesRepository {
    suspend fun getMovieList(): List<MovieModel>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsModel
}