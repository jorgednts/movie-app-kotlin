package com.example.movie_app_kotlin.di

import com.example.movie_app_kotlin.data.api.Api
import com.example.movie_app_kotlin.data.api.MoviesDataService
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSource
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSourceImpl
import com.example.movie_app_kotlin.data.repository.MoviesRepositoryImpl
import com.example.movie_app_kotlin.domain.repository.MoviesRepository
import com.example.movie_app_kotlin.domain.use_case.GetMovieDetailsUseCase
import com.example.movie_app_kotlin.domain.use_case.GetMovieDetailsUseCaseImpl
import com.example.movie_app_kotlin.domain.use_case.GetMovieListUseCase
import com.example.movie_app_kotlin.domain.use_case.GetMovieListUseCaseImpl
import com.example.movie_app_kotlin.presentation.movie_details.MovieDetailsViewModel
import com.example.movie_app_kotlin.presentation.movie_list.MovieListViewModel
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun getMoviesDataService(): MoviesDataService =
        Api.setupRetrofit().create(MoviesDataService::class.java)

    @Provides
    fun getMoviesRemoteDataSource(movieDataService: MoviesDataService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(movieDataService)

    @Provides
    fun getMoviesRepository(movieRemoteDataSource: MoviesRemoteDataSource): MoviesRepository =
        MoviesRepositoryImpl(movieRemoteDataSource)

    @Provides
    fun getMovieListUseCase(moviesRepository: MoviesRepository): GetMovieListUseCase =
        GetMovieListUseCaseImpl(moviesRepository)

    @Provides
    fun getMovieDetailsUseCase(movieRepository: MoviesRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCaseImpl(movieRepository)

    @Provides
    fun getMovieListViewModel(getMovieListUseCase: GetMovieListUseCase) =
        MovieListViewModel(getMovieListUseCase)

    @Provides
    fun getMovieDetailsViewModel(getMovieDetailsUseCase: GetMovieDetailsUseCase) =
        MovieDetailsViewModel(getMovieDetailsUseCase)
}