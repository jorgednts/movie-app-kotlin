package com.example.movie_app_kotlin

import com.example.movie_app_kotlin.data.remote.model.details.MovieDetailsResponse
import com.example.movie_app_kotlin.data.remote.model.movie.MovieResponse
import com.example.movie_app_kotlin.data.remote.model.production_companies.ProductionCompanyResponse
import com.example.movie_app_kotlin.data.remote.model.spoken_languages.SpokenLanguageResponse
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsModel
import com.example.movie_app_kotlin.domain.model.details.ProductionCompanyModel
import com.example.movie_app_kotlin.domain.model.movie.MovieModel

class UnitTestUtils {
    companion object {
        val successfulMovieListModelMock: List<MovieModel> = listOf(
            MovieModel(
                19404,
                9.3,
                "Dilwale Dulhania Le Jayenge",
                "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                listOf("Comedy", "Drama", "Romance"),
                "1995-10-20",
            ),
            MovieModel(
                278,
                8.6,
                "The Shawshank Redemption",
                "https://image.tmdb.org/t/p/w200/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                listOf("Drama", "Crime"),
                "1994-09-23",
            )
        )
        val successfulMovieListResponseMock: List<MovieResponse> = listOf(
            MovieResponse(
                19404,
                9.3,
                "Dilwale Dulhania Le Jayenge",
                "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                listOf("Comedy", "Drama", "Romance"),
                "1995-10-20",
            ),
            MovieResponse(
                278,
                8.6,
                "The Shawshank Redemption",
                "https://image.tmdb.org/t/p/w200/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                listOf("Drama", "Crime"),
                "1994-09-23",
            )
        )
        val successfulMovieDetailsModelMock: MovieDetailsModel = MovieDetailsModel(
            19404,
            false,
            13200000,
            listOf("Comedy", "Drama", "Romance"),
            "hi",
            "दिलवाले दुल्हनिया ले जायेंगे",
            "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
            "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
            listOf(
                ProductionCompanyModel(
                    1569,
                    "https://image.tmdb.org/t/p/w200/5WSkzUe6OiyKlpX2hJUghLlWkiU.png",
                    "Yash Raj Films",
                    "IN"
                )
            ),
            "1995-10-20",
            100000000,
            190,
            listOf("हिन्दी"),
            "Released",
            "Dilwale Dulhania Le Jayenge",
            9.3
        )
        val successfulMovieDetailsResponseMock: MovieDetailsResponse = MovieDetailsResponse(
            19404,
            false,
            13200000,
            listOf("Comedy", "Drama", "Romance"),
            "hi",
            "दिलवाले दुल्हनिया ले जायेंगे",
            "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
            "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
            listOf(
                ProductionCompanyResponse(
                    1569,
                    "https://image.tmdb.org/t/p/w200/5WSkzUe6OiyKlpX2hJUghLlWkiU.png",
                    "Yash Raj Films",
                    "IN"
                )
            ),
            "1995-10-20",
            100000000,
            190,
            listOf(SpokenLanguageResponse("हिन्दी")),
            "Released",
            "Dilwale Dulhania Le Jayenge",
            9.3
        )
    }
}