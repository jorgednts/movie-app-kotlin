package com.example.movie_app_kotlin.data.api

import com.example.movie_app_kotlin.data.remote.model.details.MovieDetailsResponse
import com.example.movie_app_kotlin.data.remote.model.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesDataService {
    @GET("movies")
    suspend fun getMovieList(): List<MovieResponse>

    @GET("movies/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsResponse

}