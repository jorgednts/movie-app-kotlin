package com.example.movie_app_kotlin.domain.use_case

import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel

interface GetMovieDetailsUseCase {
    suspend fun call(movieId: Int): MovieDetailsModel
}