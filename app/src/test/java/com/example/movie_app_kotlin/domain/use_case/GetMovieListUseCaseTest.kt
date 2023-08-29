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
class GetMovieListUseCaseTest {
    @MockK
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMovieListUseCase = GetMovieListUseCaseImpl(moviesRepository)
    }

    @Test
    fun getMovieListSuccessfulTest() = runTest {
        coEvery {
            moviesRepository.getMovieList()
        } returns UnitTestUtils.successfulMovieListModelMock
        val result = getMovieListUseCase.call()

        coVerify(exactly = 1) { moviesRepository.getMovieList() }
        assert(result == UnitTestUtils.successfulMovieListModelMock)
    }
}