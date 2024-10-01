package es.mrmoustard.data.source.remote.dto

import es.mrmoustard.data.mock.movieDetailDtoMock
import es.mrmoustard.data.mock.movieDetailMock
import es.mrmoustard.data.mock.topRatedWrapperMock
import es.mrmoustard.data.mock.wrapperDtoMock
import es.mrmoustard.data.source.remote.dto.mapToDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test


class MovieDetailDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = movieDetailDtoMock

        assertEquals(false, dto.adult)
        assertEquals("backdropPath", dto.backdropPath)

        dto.belongsToCollection?.let {
            assertEquals(1, it.id)
            assertEquals("name", it.name)
            assertEquals("posterPath", it.posterPath)
            assertEquals("backdropPath", it.backdropPath)
        }

        assertEquals(3000, dto.budget)
        assertEquals(3, dto.genres.size)
        assertEquals(1, dto.genres[0].id)
        assertEquals("Thriller", dto.genres[0].name)
        assertEquals(2, dto.genres[1].id)
        assertEquals("Action", dto.genres[1].name)
        assertEquals(3, dto.genres[2].id)
        assertEquals("Comedy", dto.genres[2].name)
        assertEquals("homepage", dto.homepage)
        assertEquals(1, dto.id)
        assertEquals("imdbId", dto.imdbId)
        assertEquals("originalLanguage", dto.originalLanguage)
        assertEquals("originalTitle", dto.originalTitle)
        assertEquals("overview", dto.overview)
        assertEquals(6.0, dto.popularity)
        assertEquals("posterPath", dto.posterPath)
        assertEquals(3, dto.productionCompanies.size)
        assertEquals(3, dto.productionCountries.size)
        assertEquals("releaseDate", dto.releaseDate)
        assertEquals(2, dto.revenue)
        assertEquals(3, dto.runtime)

        assertEquals(3, dto.spokenLanguages.size)
        assertEquals("1", dto.spokenLanguages[0].iso6391)
        assertEquals("name1", dto.spokenLanguages[0].name)
        assertEquals("2", dto.spokenLanguages[1].iso6391)
        assertEquals("name2", dto.spokenLanguages[1].name)
        assertEquals("3", dto.spokenLanguages[2].iso6391)
        assertEquals("name3", dto.spokenLanguages[2].name)
        assertEquals("status", dto.status)
        assertEquals("tagline", dto.tagline)
        assertEquals("title", dto.title)
        assertEquals(false, dto.video)
        assertEquals(8.2, dto.voteAverage)
        assertEquals(2400, dto.voteCount)
    }

    @Test
    fun `map an object to domain`() {
        val expected = movieDetailMock
        val actual = movieDetailDtoMock.mapToDomain()

        assertEquals(expected, actual)
    }
}