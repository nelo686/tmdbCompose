package es.mrmoustard.data.source.remote.dto

import es.mrmoustard.data.mock.productionCountryDtoMockList
import es.mrmoustard.data.mock.productionCountryMockList
import es.mrmoustard.data.source.remote.dto.mapToDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ProductionCountryDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = productionCountryDtoMockList.first()

        assertEquals("1", dto.iso31661)
        assertEquals("name1", dto.name)
    }

    @Test
    fun `map an single to domain`() {
        val expected = productionCountryMockList.first()
        val actual = productionCountryDtoMockList.first().mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map a list to domain`() {
        val expected = productionCountryMockList
        val actual = productionCountryDtoMockList.mapToDomain()

        assertEquals(expected, actual)
    }
}