package es.mrmoustard.data.source.remote

import es.mrmoustard.data.mock.movieDetailDtoMock
import es.mrmoustard.data.mock.wrapperDtoMock
import es.mrmoustard.data.source.dto.MovieDetailDto
import es.mrmoustard.data.source.dto.WrapperDto
import es.mrmoustard.data.source.remote.api.TmdbApi
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

internal class MoviesRemoteDataSourceImplTest {

    @Mock private lateinit var api: TmdbApi

    private lateinit var remote: MoviesRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remote = MoviesRemoteDataSourceImpl(api)
    }

    @Test
    fun `GIVEN a page number and a region, WHEN call getTopRated(), THEN a list of movies is retrieved`() {
        runBlocking {
            // Given
            val page = 1
            val region = "ES"
            whenever(api.getTopRated(any(), any())).thenReturn(wrapperDtoMock)

            // When
            val result = remote.getTopRated(page, region)

            // Then
            assertThat(result, instanceOf(WrapperDto::class.java))
            assertEquals(page, result.page)
            assertEquals(1, result.results.size)
            assertEquals(1, result.totalPages)
            assertEquals(1, result.totalResults)
        }
    }

    @Test
    fun `GIVEN a movie id, WHEN call getMovieDetails(), THEN movie details are retrieved`() {
        runBlocking {
            // Given
            val movieId = 1
            whenever(api.getMovieDetails(any())).thenReturn(movieDetailDtoMock)

            // When
            val result = remote.getMovieDetails(movieId)

            // Then
            assertThat(result, instanceOf(MovieDetailDto::class.java))
            assertEquals(false, result.adult)
            assertEquals("backdropPath", result.backdropPath)

            result.belongsToCollection?.let {
                assertEquals(1, it.id)
                assertEquals("name", it.name)
                assertEquals("posterPath", it.posterPath)
                assertEquals("backdropPath", it.backdropPath)
            }

            assertEquals(3000, result.budget)
            assertEquals(3, result.genres.size)
            assertEquals(1, result.genres[0].id)
            assertEquals("Thriller", result.genres[0].name)
            assertEquals(2, result.genres[1].id)
            assertEquals("Action", result.genres[1].name)
            assertEquals(3, result.genres[2].id)
            assertEquals("Comedy", result.genres[2].name)
            assertEquals("homepage", result.homepage)
            assertEquals(1, result.id)
            assertEquals("imdbId", result.imdbId)
            assertEquals("originalLanguage", result.originalLanguage)
            assertEquals("originalTitle", result.originalTitle)
            assertEquals("overview", result.overview)
            assertEquals(6.0, result.popularity)
            assertEquals("posterPath", result.posterPath)
            assertEquals(3, result.productionCompanies.size)
            assertEquals(3, result.productionCountries.size)
            assertEquals("releaseDate", result.releaseDate)
            assertEquals(2, result.revenue)
            assertEquals(3, result.runtime)

            assertEquals(3, result.spokenLanguages.size)
            assertEquals("1", result.spokenLanguages[0].iso6391)
            assertEquals("name1", result.spokenLanguages[0].name)
            assertEquals("2", result.spokenLanguages[1].iso6391)
            assertEquals("name2", result.spokenLanguages[1].name)
            assertEquals("3", result.spokenLanguages[2].iso6391)
            assertEquals("name3", result.spokenLanguages[2].name)
            assertEquals("status", result.status)
            assertEquals("tagline", result.tagline)
            assertEquals("title", result.title)
            assertEquals(false, result.video)
            assertEquals(8.2, result.voteAverage)
            assertEquals(2400, result.voteCount)
        }
    }
}