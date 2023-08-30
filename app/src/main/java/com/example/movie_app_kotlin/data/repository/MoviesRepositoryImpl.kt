package com.example.movie_app_kotlin.data.repository

import com.example.movie_app_kotlin.data.cache.MoviesCacheDataSource
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSource
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel
import com.example.movie_app_kotlin.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesCacheDataSource: MoviesCacheDataSource
) :
    MoviesRepository {
    override suspend fun getMovieList(): List<MovieModel> {
        return try {
            val movieList = moviesRemoteDataSource.getMovieList()
            moviesCacheDataSource.saveMovieList(movieList)
            moviesCacheDataSource.getMovieList()
        } catch (e: Exception) {
            try {
                moviesCacheDataSource.getMovieList()
            } catch (_: Exception) {
                throw e
            }
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel {
        return try {
            val movieDetails = moviesRemoteDataSource.getMovieDetails(movieId)
            moviesCacheDataSource.saveMovieDetails(movieDetails)
            moviesCacheDataSource.getMovieDetails(movieId)
        } catch (e: Exception) {
            try {
                moviesCacheDataSource.getMovieDetails(movieId)
            } catch (_: Exception) {
                throw e
            }
        }
    }

}