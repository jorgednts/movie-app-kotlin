package com.example.movie_app_kotlin.domain.model.details

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "movie_details")
@TypeConverters(MovieDetailsConverter::class)
data class MovieDetailsCache(
    @PrimaryKey val id: Int,
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

class MovieDetailsConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType =
            object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromProductionCompany(value: String): List<ProductionCompanyModel> {
        val listType =
            object : TypeToken<List<ProductionCompanyModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toProductionCompany(list: List<ProductionCompanyModel>): String {
        return Gson().toJson(list)
    }
}
