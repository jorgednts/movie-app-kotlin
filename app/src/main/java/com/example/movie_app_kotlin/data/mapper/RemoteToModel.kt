package com.example.movie_app_kotlin.data.mapper

import com.example.movie_app_kotlin.constants.NullResponseConstants
import com.example.movie_app_kotlin.data.remote.model.details.MovieDetailsResponse
import com.example.movie_app_kotlin.data.remote.model.movie.MovieResponse
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.details.ProductionCompanyModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel

fun List<MovieResponse>.convertToMovieListModel(): List<MovieModel> {
    return this.map { movieListResponse ->
        MovieModel(
            movieListResponse.id ?: NullResponseConstants.NULL_INT_RESPONSE,
            movieListResponse.voteAverage ?: NullResponseConstants.NULL_DOUBLE_RESPONSE,
            movieListResponse.title ?: NullResponseConstants.NULL_STRING_RESPONSE,
            movieListResponse.posterUrl ?: NullResponseConstants.NULL_STRING_RESPONSE,
            movieListResponse.genres?.map {
                it
            } ?: emptyList(),
            movieListResponse.releaseDate ?: NullResponseConstants.NULL_STRING_RESPONSE
        )
    }
}

fun MovieDetailsResponse.convertToMovieDetailsModel(): MovieDetailsModel {
    return MovieDetailsModel(
        this.id ?: NullResponseConstants.NULL_INT_RESPONSE,
        this.adult ?: NullResponseConstants.NULL_BOOL_RESPONSE,
        this.budget ?: NullResponseConstants.NULL_INT_RESPONSE,
        this.genres?.map {
            it
        } ?: emptyList(),
        this.originalLanguage ?: NullResponseConstants.NULL_STRING_RESPONSE,
        this.originalTitle ?: NullResponseConstants.NULL_STRING_RESPONSE,
        this.overview ?: NullResponseConstants.NULL_STRING_RESPONSE,
        this.posterUrl ?: NullResponseConstants.NULL_STRING_RESPONSE,
        this.productionCompanies?.map {
            ProductionCompanyModel(
                it.id ?: NullResponseConstants.NULL_INT_RESPONSE,
                it.logoUrl ?: NullResponseConstants.NULL_STRING_RESPONSE,
                it.name ?: NullResponseConstants.NULL_STRING_RESPONSE,
                it.originCountry ?: NullResponseConstants.NULL_STRING_RESPONSE
            )
        } ?: emptyList(),
        this.releaseDate ?: NullResponseConstants.NULL_STRING_RESPONSE,
        this.revenue ?: NullResponseConstants.NULL_INT_RESPONSE,
        this.runtime ?: NullResponseConstants.NULL_INT_RESPONSE,
        this.spokenLanguages?.map {
            it.name ?: NullResponseConstants.NULL_STRING_RESPONSE
        } ?: emptyList(),
        this.status ?: NullResponseConstants.NULL_STRING_RESPONSE,
        this.title ?: NullResponseConstants.NULL_STRING_RESPONSE,
        this.voteAverage ?: NullResponseConstants.NULL_DOUBLE_RESPONSE

    )
}