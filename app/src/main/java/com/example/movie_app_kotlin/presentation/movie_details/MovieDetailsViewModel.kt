package com.example.movie_app_kotlin.presentation.movie_details

import androidx.lifecycle.ViewModel
import com.example.movie_app_kotlin.domain.use_case.GetMovieDetailsUseCase

class MovieDetailsViewModel(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    ViewModel() {
}