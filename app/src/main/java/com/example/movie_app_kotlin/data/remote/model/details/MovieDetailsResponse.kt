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
    @SerializedName("originalLanguage")
    val originalLanguage: String?,
    @SerializedName("originalTitle")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("posterUrl")
    val posterUrl: String?,
    @SerializedName("productionCompanies")
    val productionCompanies: List<ProductionCompanyResponse>?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spokenLanguages")
    val spokenLanguages: List<SpokenLanguageResponse>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("voteAverage")
    val voteAverage: Double?
)
