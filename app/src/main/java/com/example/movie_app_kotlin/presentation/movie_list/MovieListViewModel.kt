package com.example.movie_app_kotlin.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app_kotlin.domain.exception.CustomNetworkException
import com.example.movie_app_kotlin.domain.model.movie.MovieModel
import com.example.movie_app_kotlin.domain.use_case.GetMovieListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieListViewModel(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {

    private val _movieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    fun getMovieList() {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)
            try {
                val movies = getMovieListUseCase.call()
                _isLoading.postValue(false)
                _movieList.postValue(movies)
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