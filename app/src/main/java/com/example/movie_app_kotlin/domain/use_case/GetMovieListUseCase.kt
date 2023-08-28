package com.example.movie_app_kotlin.domain.use_case

import com.example.movie_app_kotlin.domain.model.movie.MovieModel

interface GetMovieListUseCase {
    suspend fun call() : List<MovieModel>
}