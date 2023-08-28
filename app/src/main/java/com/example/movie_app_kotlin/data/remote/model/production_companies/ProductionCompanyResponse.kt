package com.example.movie_app_kotlin.data.remote.model.production_companies

import com.google.gson.annotations.SerializedName

data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logoUrl")
    val logoUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("originCountry")
    val originCountry: String?
)
