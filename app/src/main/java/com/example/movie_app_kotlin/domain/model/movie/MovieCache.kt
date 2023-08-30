package com.example.movie_app_kotlin.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "movies")
@TypeConverters(MovieConverter::class)
data class MovieCache(
    @PrimaryKey val id: Int,
    val voteAverage: Double,
    val title: String,
    val posterUrl: String,
    val genres: List<String>,
    val releaseDate: String
)

class MovieConverter {
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
}