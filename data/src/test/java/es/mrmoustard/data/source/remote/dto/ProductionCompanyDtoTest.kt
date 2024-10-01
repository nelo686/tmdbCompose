package es.mrmoustard.data.source.remote.dto

import es.mrmoustard.data.mock.productionCompanyDtoMockList
import es.mrmoustard.data.mock.productionCompanyMockList
import es.mrmoustard.data.source.remote.dto.mapToDomain
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class ProductionCompanyDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = productionCompanyDtoMockList.first()

        assertEquals(1, dto.id)
        assertEquals("name1", dto.name)
        assertEquals("logoPath1", dto.logoPath)
        assertEquals("originCountry1", dto.originCountry)
    }

    @Test
    fun `constructor with null param should works well`() {
        val dto = productionCompanyDtoMockList.last()

        assertEquals(3, dto.id)
        assertEquals("name3", dto.name)
        assertNull(dto.logoPath)
        assertEquals("originCountry3", dto.originCountry)
    }

    @Test
    fun `map an single to domain`() {
        val expected = productionCompanyMockList.first()
        val actual = productionCompanyDtoMockList.first().mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map an object to domain with a null param`() {
        val expected = productionCompanyMockList.last()
        val actual = productionCompanyDtoMockList.last().mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map a list to domain`() {
        val expected = productionCompanyMockList
        val actual = productionCompanyDtoMockList.mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map a list to domain with a null param`() {
        val expected = productionCompanyMockList
        val actual = productionCompanyDtoMockList.mapToDomain()

        assertEquals(expected, actual)
    }
}