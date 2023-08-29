package com.example.movie_app_kotlin.data.remote_data_source

import com.example.movie_app_kotlin.UnitTestUtils
import com.example.movie_app_kotlin.data.api.MoviesDataService
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSource
import com.example.movie_app_kotlin.data.remote.data_source.MoviesRemoteDataSourceImpl
import com.example.movie_app_kotlin.domain.exception.CustomNetworkException
import com.example.movie_app_kotlin.domain.exception.GenericErrorException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class MoviesRemoteDataSourceTest {
    @MockK
    private lateinit var moviesDataService: MoviesDataService
    private lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    private var movieId = 19404

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesRemoteDataSource = MoviesRemoteDataSourceImpl(moviesDataService)
    }

    @Test
    fun getMovieListSuccessfulTest() = runTest {
        coEvery {
            moviesDataService.getMovieList()
        } returns UnitTestUtils.successfulMovieListResponseMock
        val result = moviesRemoteDataSource.getMovieList()

        coVerify(exactly = 1) { moviesDataService.getMovieList() }
        assert(result == UnitTestUtils.successfulMovieListModelMock)
    }

    @Test
    fun getMovieListNetworkErrorTest() = runTest {
        coEvery {
            moviesDataService.getMovieList()
        } throws UnknownHostException()
        try {
            moviesRemoteDataSource.getMovieList()
            fail("Expected CustomNetworkException")
        } catch (_: CustomNetworkException) {
        }
    }

    @Test
    fun getMovieListGenericErrorTest() = runTest {
        coEvery {
            moviesDataService.getMovieList()
        } throws Exception()
        try {
            moviesRemoteDataSource.getMovieList()
            fail("Expected GenericErrorException")
        } catch (e: GenericErrorException) {
        }
    }

    @Test
    fun getMovieDetailsSuccessfulTest() = runTest {
        coEvery {
            moviesDataService.getMovieDetails(movieId)
        } returns UnitTestUtils.successfulMovieDetailsResponseMock
        val result = moviesRemoteDataSource.getMovieDetails(movieId)

        coVerify(exactly = 1) { moviesDataService.getMovieDetails(movieId) }
        assert(result == UnitTestUtils.successfulMovieDetailsModelMock)
    }

    @Test
    fun getMovieDetailsNetworkErrorTest() = runTest {
        coEvery {
            moviesDataService.getMovieDetails(movieId)
        } throws UnknownHostException()
        try {
            moviesRemoteDataSource.getMovieDetails(movieId)
            fail("Expected CustomNetworkException")
        } catch (e: CustomNetworkException) {
        }
    }

    @Test
    fun getMovieDetailsGenericErrorTest() = runTest {
        coEvery {
            moviesDataService.getMovieDetails(movieId)
        } throws Exception()
        try {
            moviesRemoteDataSource.getMovieDetails(movieId)
            fail("Expected GenericErrorException")
        } catch (e: GenericErrorException) {
        }
    }
}