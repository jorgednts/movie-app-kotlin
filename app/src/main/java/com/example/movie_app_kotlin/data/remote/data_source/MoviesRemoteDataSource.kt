package com.example.movie_app_kotlin.data.remote.data_source

import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel

interface MoviesRemoteDataSource {
    suspend fun getMovieList(): List<MovieModel>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsModel
}