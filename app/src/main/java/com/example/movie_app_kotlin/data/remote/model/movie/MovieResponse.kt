package com.example.movie_app_kotlin.data.remote.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_url")
    val posterUrl: String?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("release_date")
    val releaseDate: String?
)