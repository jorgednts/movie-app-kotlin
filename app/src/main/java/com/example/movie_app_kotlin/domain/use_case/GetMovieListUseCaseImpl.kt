package com.example.movie_app_kotlin.domain.use_case

import com.example.movie_app_kotlin.domain.model.movie.MovieModel
import com.example.movie_app_kotlin.domain.repository.MoviesRepository

class GetMovieListUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetMovieListUseCase {
    override suspend fun call(): List<MovieModel> {
        return moviesRepository.getMovieList()
    }
}