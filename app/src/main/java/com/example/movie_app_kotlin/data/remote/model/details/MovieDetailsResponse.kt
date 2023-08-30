package com.example.movie_app_kotlin.data.remote.model.details

import com.example.movie_app_kotlin.data.remote.model.production_companies.ProductionCompanyResponse
import com.example.movie_app_kotlin.data.remote.model.spoken_languages.SpokenLanguageResponse
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_url")
    val posterUrl: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)
