package com.example.movie_app_kotlin.data.cache

import com.example.movie_app_kotlin.data.mapper.toMovieDetailsCache
import com.example.movie_app_kotlin.data.mapper.toMovieDetailsModel
import com.example.movie_app_kotlin.data.mapper.toMovieListCache
import com.example.movie_app_kotlin.data.mapper.toMovieListModel
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsDao
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieDao
import com.example.movie_app_kotlin.domain.model.movie.MovieModel

class MoviesCacheDataSourceImpl(
    private val movieDao: MovieDao,
    private val moviesDetailsDao: MovieDetailsDao
) : MoviesCacheDataSource {

    override suspend fun saveMovieList(movieList: List<MovieModel>) {
        movieDao.insertMovies(movieList.toMovieListCache())
    }

    override suspend fun getMovieList(): List<MovieModel> {
        val result = movieDao.getMovieList()
        if (!result.isNullOrEmpty()) {
            return result.toMovieListModel()
        }
        throw Exception()
    }

    override suspend fun saveMovieDetails(movieDetailsModel: MovieDetailsModel) {
        moviesDetailsDao.insertMovieDetails(movieDetailsModel.toMovieDetailsCache())
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel {
        val result = moviesDetailsDao.getMovieDetails(movieId)
        if (result != null) {
            return result.toMovieDetailsModel()
        }
        throw Exception()
    }
}