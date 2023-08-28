package com.example.movie_app_kotlin.domain.use_case

import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.repository.MoviesRepository

class GetMovieDetailsUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetMovieDetailsUseCase {
    override suspend fun call(movieId: Int): MovieDetailsModel {
        return moviesRepository.getMovieDetails(movieId)
    }
}