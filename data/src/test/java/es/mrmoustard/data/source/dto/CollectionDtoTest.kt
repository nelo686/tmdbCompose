package es.mrmoustard.data.source.dto

import es.mrmoustard.data.mock.collectionDtoMock
import es.mrmoustard.data.mock.collectionMock
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class CollectionDtoTest {

    @Test
    fun `constructor should works well`() {
        val dto = collectionDtoMock

        assertEquals(1, dto.id)
        assertEquals("name", dto.name)
        assertEquals("posterPath", dto.posterPath)
        assertEquals("backdropPath", dto.backdropPath)
    }

    @Test
    fun `constructor with null param should works well`() {
        val dto = collectionDtoMock.copy(posterPath = null)

        assertEquals(1, dto.id)
        assertEquals("name", dto.name)
        assertNull(dto.posterPath)
        assertEquals("backdropPath", dto.backdropPath)
    }

    @Test
    fun `map to domain`() {
        val expected = collectionMock
        val actual = collectionDtoMock.mapToDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map to domain with a null param`() {
        val expected = collectionMock.copy(posterPath = "")
        val actual = collectionDtoMock.copy(posterPath = null).mapToDomain()

        assertEquals(expected, actual)
    }
}