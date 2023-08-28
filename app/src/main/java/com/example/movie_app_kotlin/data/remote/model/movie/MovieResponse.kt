package com.example.movie_app_kotlin.data.remote.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("voteAverage")
    val voteAverage: Double?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("posterUrl")
    val posterUrl: String?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("releaseDate")
    val releaseDate: String?
)