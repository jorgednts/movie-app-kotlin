package com.example.movie_app_kotlin.domain.model.movie

data class MovieModel(
    val id: Int,
    val voteAverage: Double,
    val title: String,
    val posterUrl: String,
    val genres: List<String>,
    val releaseDate: String
)