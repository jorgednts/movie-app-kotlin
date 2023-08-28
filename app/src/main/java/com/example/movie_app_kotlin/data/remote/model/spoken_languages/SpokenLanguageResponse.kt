package com.example.movie_app_kotlin.data.remote.model.spoken_languages

import com.google.gson.annotations.SerializedName

data class SpokenLanguageResponse(
    @SerializedName("name")
    val name: String?
)
