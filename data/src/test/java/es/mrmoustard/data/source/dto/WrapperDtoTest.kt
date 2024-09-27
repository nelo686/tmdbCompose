package es.mrmoustard.data.source.dto

import es.mrmoustard.data.mock.topRatedWrapperMock
import es.mrmoustard.data.mock.wrapperDtoMock
import es.mrmoustard.data.source.remote.dto.mapToDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test

class WrapperDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = wrapperDtoMock

        assertEquals(1, dto.page)
        assertEquals(1, dto.results.size)
        assertEquals(1, dto.totalPages)
        assertEquals(1, dto.totalResults)
    }

    @Test
    fun `map an object to domain`() {
        val expected = topRatedWrapperMock
        val actual = wrapperDtoMock.mapToDomain()

        assertEquals(expected, actual)
    }
}