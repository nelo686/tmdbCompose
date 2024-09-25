package es.mrmoustard.data.source.dto

import es.mrmoustard.data.mock.genreDtoMockList
import es.mrmoustard.data.mock.genreMockList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GenreDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = genreDtoMockList.first()

        assertEquals(1, dto.id)
        assertEquals("Thriller", dto.name)
    }

    @Test
    fun `map to domain a single object`() {
        val expected = genreMockList.first()
        val actual = genreDtoMockList.first().mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map to domain a list`() {
        val expected = genreMockList
        val actual = genreDtoMockList.mapToDomain()

        assertEquals(expected, actual)
    }
}