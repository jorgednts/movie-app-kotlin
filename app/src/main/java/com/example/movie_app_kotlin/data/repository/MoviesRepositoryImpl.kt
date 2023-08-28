package com.example.movie_app_kotlin.data.repository

import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSource
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel
import com.example.movie_app_kotlin.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val moviesRemoteDataSource: MoviesRemoteDataSource) :
    MoviesRepository {
    override suspend fun getMovieList(): List<MovieModel> {
        return moviesRemoteDataSource.getMovieList()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel {
        return moviesRemoteDataSource.getMovieDetails(movieId)
    }

}