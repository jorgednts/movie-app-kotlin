package com.example.movie_app_kotlin.data.repository

import com.example.movie_app_kotlin.UnitTestUtils
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSource
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
class MoviesRepositoryTest {

    @MockK
    private lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    private lateinit var moviesRepository: MoviesRepository
    private var movieId = 19404

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesRepository = MoviesRepositoryImpl(moviesRemoteDataSource)
    }

    @Test
    fun getMovieListSuccessfulTest() = runTest {
        coEvery {
            moviesRemoteDataSource.getMovieList()
        } returns UnitTestUtils.successfulMovieListModelMock
        val result = moviesRepository.getMovieList()

        coVerify(exactly = 1) { moviesRemoteDataSource.getMovieList() }
        assert(result == UnitTestUtils.successfulMovieListModelMock)
    }

    @Test
    fun getMovieDetailsSuccessfulTest() = runTest {
        coEvery {
            moviesRemoteDataSource.getMovieDetails(movieId)
        } returns UnitTestUtils.successfulMovieDetailsModelMock
        val result = moviesRepository.getMovieDetails(movieId)

        coVerify(exactly = 1) { moviesRemoteDataSource.getMovieDetails(movieId) }
        assert(result == UnitTestUtils.successfulMovieDetailsModelMock)
    }
}