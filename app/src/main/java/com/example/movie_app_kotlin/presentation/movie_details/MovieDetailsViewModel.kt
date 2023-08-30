package com.example.movie_app_kotlin.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app_kotlin.domain.exception.CustomNetworkException
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.use_case.GetMovieDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) :
    ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetailsModel>()
    val movieDetails: LiveData<MovieDetailsModel> = _movieDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)
            try {
                val details = getMovieDetailsUseCase.call(movieId)
                _movieDetails.postValue(details)
                _isLoading.postValue(false)
                _errorMessage.postValue("")
            } catch (e: CustomNetworkException) {
                _isLoading.postValue(false)
                _errorMessage.postValue("Network error!")
            } catch (e: Exception) {
                _isLoading.postValue(false)
                _errorMessage.postValue("An error has occurred!")
            }
        }
    }
}