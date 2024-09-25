package es.mrmoustard.data.repository

import es.mrmoustard.data.mock.movieDetailDtoMock
import es.mrmoustard.data.mock.wrapperDtoMock
import es.mrmoustard.data.source.remote.MoviesRemoteDataSource
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.domain.model.TopRatedWrapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class MoviesRepositoryTest {

    @Mock private lateinit var remote: MoviesRemoteDataSource

    private lateinit var repository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MoviesRepository(remote)
    }

    @Test
    fun `GIVEN a page number and a region, WHEN call getTopRated(), THEN a list of movies is retrieved`() {
        runBlocking {
            // Given
            val page = 1
            val region = "ES"
            whenever(remote.getTopRated(any(), any())).thenReturn(wrapperDtoMock)

            // When
            val result = repository.getTopRated(page, region).getOrNull()

            // Then
            assertThat(result, instanceOf(TopRatedWrapper::class.java))
            assertEquals(1, (result as TopRatedWrapper).results.size)
            assertThat(result.results.first(), instanceOf(Movie::class.java))
        }
    }

    @Test
    fun `GIVEN a movie id, WHEN call getMovieDetails(), THEN movie details are retrieved`() {
        runBlocking {
            // Given
            val movieId = 1
            whenever(remote.getMovieDetails(any())).thenReturn(movieDetailDtoMock)

            // When
            val result = repository.getMovieDetails(movieId).getOrNull()

            // Then
            assertThat(result, instanceOf(MovieDetail::class.java))
        }
    }
}
