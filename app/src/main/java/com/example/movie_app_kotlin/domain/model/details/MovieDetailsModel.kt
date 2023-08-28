package com.example.movie_app_kotlin.domain.model.details

data class MovieDetailsModel(
    val id: Int,
    val adult: Boolean,
    val budget: Int,
    val genres: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String,
    val productionCompanies: List<ProductionCompanyModel>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<String>,
    val status: String,
    val title: String,
    val voteAverage: Double
)
