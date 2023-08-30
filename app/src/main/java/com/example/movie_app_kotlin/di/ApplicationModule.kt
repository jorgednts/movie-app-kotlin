package com.example.movie_app_kotlin.di

import android.content.Context
import com.example.movie_app_kotlin.data.api.Api
import com.example.movie_app_kotlin.data.api.MoviesDataService
import com.example.movie_app_kotlin.data.cache.MoviesCacheDataSource
import com.example.movie_app_kotlin.data.cache.MoviesCacheDataSourceImpl
import com.example.movie_app_kotlin.data.local_database.MoviesLocalDataBase
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSource
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSourceImpl
import com.example.movie_app_kotlin.data.repository.MoviesRepositoryImpl
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsDao
import com.example.movie_app_kotlin.domain.model.movie.MovieDao
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
class ApplicationModule(private var context: Context) {

    @Provides
    fun getContext(): Context = context

    @Provides
    fun getMoviesDataService(): MoviesDataService =
        Api.setupRetrofit().create(MoviesDataService::class.java)

    @Provides
    fun getMoviesLocalDataBase(): MoviesLocalDataBase =
        MoviesLocalDataBase.getInstance(context)

    @Provides
    fun getMoviesDao(moviesLocalDatabase: MoviesLocalDataBase): MovieDao {
        return moviesLocalDatabase.movieDao()
    }

    @Provides
    fun getMovieDetailsDao(moviesLocalDatabase: MoviesLocalDataBase): MovieDetailsDao {
        return moviesLocalDatabase.movieDetailsDao()
    }

    @Provides
    fun getMoviesCacheDataSource(
        moviesDao: MovieDao,
        movieDetailsDao: MovieDetailsDao
    ): MoviesCacheDataSource {
        return MoviesCacheDataSourceImpl(moviesDao, movieDetailsDao)
    }

    @Provides
    fun getMoviesRemoteDataSource(movieDataService: MoviesDataService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(movieDataService)

    @Provides
    fun getMoviesRepository(
        movieRemoteDataSource: MoviesRemoteDataSource,
        moviesCacheDataSource: MoviesCacheDataSource
    ): MoviesRepository =
        MoviesRepositoryImpl(movieRemoteDataSource, moviesCacheDataSource)

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