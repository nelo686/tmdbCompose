package es.mrmoustard.data.source.dto

import es.mrmoustard.data.mock.productionCountryDtoMockList
import es.mrmoustard.data.mock.productionCountryMockList
import es.mrmoustard.data.mock.spokenLanguageDtoMockList
import es.mrmoustard.data.mock.spokenLanguageMockList
import es.mrmoustard.data.source.remote.dto.mapToDomain
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SpokenLanguageDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = spokenLanguageDtoMockList.first()

        assertEquals("1", dto.iso6391)
        assertEquals("name1", dto.name)
    }

    @Test
    fun `map an single to domain`() {
        val expected = spokenLanguageMockList.first()
        val actual = spokenLanguageDtoMockList.first().mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map a list to domain`() {
        val expected = spokenLanguageMockList
        val actual = spokenLanguageDtoMockList.mapToDomain()

        assertEquals(expected, actual)
    }
}