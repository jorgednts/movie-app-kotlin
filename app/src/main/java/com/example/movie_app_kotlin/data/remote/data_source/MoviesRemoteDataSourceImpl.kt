package com.example.movie_app_kotlin.data.remote.data_source

import com.example.movie_app_kotlin.data.api.MoviesDataService
import com.example.movie_app_kotlin.data.mapper.convertToMovieDetailsModel
import com.example.movie_app_kotlin.data.mapper.convertToMovieListModel
import com.example.movie_app_kotlin.domain.exception.CustomNetworkException
import com.example.movie_app_kotlin.domain.exception.GenericErrorException
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MoviesRemoteDataSourceImpl(private val moviesDataService: MoviesDataService) :
    MoviesRemoteDataSource {
    override suspend fun getMovieList(): List<MovieModel> {
        try {
            val response = moviesDataService.getMovieList()
            return response.convertToMovieListModel()
        } catch (e: UnknownHostException) {
            throw CustomNetworkException()
        } catch (e: SocketTimeoutException) {
            throw CustomNetworkException()
        } catch (e: Exception) {
            throw GenericErrorException()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel {
        try {
            val response = moviesDataService.getMovieDetails(movieId)
            return response.convertToMovieDetailsModel()
        } catch (e: UnknownHostException) {
            throw CustomNetworkException()
        } catch (e: SocketTimeoutException) {
            throw CustomNetworkException()
        } catch (e: Exception) {
            throw GenericErrorException()
        }
    }
}