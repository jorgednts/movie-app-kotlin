package com.example.movie_app_kotlin.domain.use_case

import com.example.movie_app_kotlin.UnitTestUtils
import com.example.movie_app_kotlin.domain.repository.MoviesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieDetailsUseCaseTest {
    @MockK
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private var movieId = 19404

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMovieDetailsUseCase = GetMovieDetailsUseCaseImpl(moviesRepository)
    }

    @Test
    fun getMovieDetailsSuccessfulTest() = runTest {
        coEvery {
            moviesRepository.getMovieDetails(movieId)
        } returns UnitTestUtils.successfulMovieDetailsModelMock

        val result = getMovieDetailsUseCase.call(movieId)
        coVerify(exactly = 1) {
            moviesRepository.getMovieDetails(movieId)
        }
        assert(result == UnitTestUtils.successfulMovieDetailsModelMock)
    }
}