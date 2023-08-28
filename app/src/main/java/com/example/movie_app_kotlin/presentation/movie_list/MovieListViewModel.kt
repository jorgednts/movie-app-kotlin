package com.example.movie_app_kotlin.presentation.movie_list

import androidx.lifecycle.ViewModel
import com.example.movie_app_kotlin.domain.use_case.GetMovieListUseCase

class MovieListViewModel(private val getMovieListUseCase: GetMovieListUseCase) : ViewModel() {
}