package com.example.movie_app_kotlin.data.cache

import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel

interface MoviesCacheDataSource {
    suspend fun saveMovieList(movieList: List<MovieModel>)
    suspend fun getMovieList(): List<MovieModel>
    suspend fun saveMovieDetails(movieDetailsModel: MovieDetailsModel)
    suspend fun getMovieDetails(movieId: Int): MovieDetailsModel
}