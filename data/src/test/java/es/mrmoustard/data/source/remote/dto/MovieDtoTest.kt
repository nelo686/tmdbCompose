package es.mrmoustard.data.source.remote.dto

import es.mrmoustard.data.mock.movieDtoMock
import es.mrmoustard.data.mock.movieMock
import es.mrmoustard.data.source.remote.dto.mapToDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = movieDtoMock

        assertEquals(false, dto.adult)
        assertEquals("backdropPath", dto.backdropPath)
        assertEquals(1, dto.genreIds[0])
        assertEquals(2, dto.genreIds[1])
        assertEquals(3, dto.genreIds[2])
        assertEquals(3, dto.genreIds.size)
        assertEquals(1, dto.id)
        assertEquals("originalLanguage", dto.originalLanguage)
        assertEquals("originalTitle", dto.originalTitle)
        assertEquals("overview", dto.overview)
        assertEquals(6.0, dto.popularity)
        assertEquals("posterPath", dto.posterPath)
        assertEquals("releaseDate", dto.releaseDate)
        assertEquals("title", dto.title)
        assertEquals(false, dto.video)
        assertEquals(8.2, dto.voteAverage)
        assertEquals(2400, dto.voteCount)
    }

    @Test
    fun `map an object to domain`() {
        val expected = movieMock
        val actual = movieDtoMock.mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map a list to domain`() {
        val expected = listOf(movieMock, movieMock.copy(backdropPath = ""))
        val actual = listOf(movieDtoMock, movieDtoMock.copy(backdropPath = null)).mapToDomain()

        assertEquals(expected, actual)
    }
}