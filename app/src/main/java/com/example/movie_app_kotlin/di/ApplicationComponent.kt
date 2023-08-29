package com.example.movie_app_kotlin.di

import com.example.movie_app_kotlin.presentation.movie_list.MovieListActivity
import com.example.movie_app_kotlin.presentation.movie_details.MovieDetailsActivity
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun injectInMovieListActivity(movieListActivity: MovieListActivity)
    fun injectInMovieDetailsActivity(movieDetailsActivity: MovieDetailsActivity)
}