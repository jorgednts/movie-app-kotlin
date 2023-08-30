package com.example.movie_app_kotlin.data.mapper

import com.example.movie_app_kotlin.domain.model.details.MovieDetailsCache
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.movie.MovieCache
import com.example.movie_app_kotlin.domain.model.movie.MovieModel

fun List<MovieModel>.toMovieListCache(): List<MovieCache> {
    return this.map {
        MovieCache(
            it.id,
            it.voteAverage,
            it.title,
            it.posterUrl,
            it.genres,
            it.releaseDate
        )
    }
}

fun MovieDetailsModel.toMovieDetailsCache(): MovieDetailsCache {
    return MovieDetailsCache(
        id,
        adult,
        budget,
        genres,
        originalLanguage,
        originalTitle,
        overview,
        posterUrl,
        productionCompanies.map { it },
        releaseDate,
        revenue,
        runtime,
        spokenLanguages,
        status,
        title,
        voteAverage
    )
}