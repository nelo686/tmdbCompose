package es.mrmoustard.data.source.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.Throws

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var db: MoviesDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun findAMovieStatusById() {
        runBlocking {
            // Given
            val expected = MovieStatus(
                id = 1,
                title = "title",
                backdropPath = "backdropPath",
                favourite = true
            )

            // When
            dao.insert(expected)
            val result = dao.findById(id = expected.id)

            // Then
            assertNotNull(result)
            result?.let {
                assertEquals(expected.id, it.id)
                assertEquals(expected.title, it.title)
                assertEquals(expected.backdropPath, it.backdropPath)
                assertTrue(it.favourite)
                assertFalse(it.wannaWatchIt)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun findANonExistingMovieStatusByIdAndGetNull() {
        runBlocking {
            // Given
            val expected = MovieStatus(
                id = 2,
                title = "title",
                backdropPath = "backdropPath",
                favourite = true
            )

            // When
            dao.insert(expected)
            val result = dao.findById(id = 100)

            // Then
            assertNull(result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertAFavouriteMovieAndGetItem() {
        runBlocking {
            // Given
            val expected = MovieStatus(
                id = 1,
                title = "title",
                backdropPath = "backdropPath",
                favourite = true
            )

            // When
            dao.insert(expected)

            // Then
            val result = dao.findFavourites().first()
            assertEquals(expected.id, result.id)
            assertEquals(expected.title, result.title)
            assertEquals(expected.backdropPath, result.backdropPath)
            assertTrue(result.favourite)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertAWannaWatchItMovieAndGetItem() {
        runBlocking {
            // Given
            val expected = MovieStatus(
                id = 1,
                title = "title",
                backdropPath = "backdropPath",
                wannaWatchIt = true
            )

            // When
            dao.insert(expected)

            // Then
            val result = dao.findMoviesToWatch().first()
            assertEquals(expected.id, result.id)
            assertEquals(expected.title, result.title)
            assertEquals(expected.backdropPath, result.backdropPath)
            assertTrue(result.wannaWatchIt)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteAMovieStatus() {
        runBlocking {
            // Given
            val item = MovieStatus(
                id = 1,
                title = "title",
                backdropPath = "backdropPath",
                wannaWatchIt = true
            )

            // When
            dao.insert(item)
            dao.delete(item)
            val result = dao.findById(id = item.id)

            // Then
            assertNull(result)
        }
    }

    @Test
    @Throws(Exception::class)
    fun updateMovieFavouriteStatus() {
        runBlocking {
            // Given
            val item = MovieStatus(
                id = 1,
                title = "title",
                backdropPath = "backdropPath",
                wannaWatchIt = true
            )

            // When
            dao.insert(item)
            dao.update(
                movieId = item.id,
                favourite = true,
                wannaWatchIt = item.wannaWatchIt
            )
            val result = dao.findById(id = item.id)

            // Then
            assertNotNull(result)
            result?.let {
                assertEquals(item.id, it.id)
                assertTrue(it.favourite)
                assertTrue(it.wannaWatchIt)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun updateMovieWannaWatchItStatus() {
        runBlocking {
            // Given
            val item = MovieStatus(
                id = 1,
                title = "title",
                backdropPath = "backdropPath",
                favourite = true
            )

            // When
            dao.insert(item)
            dao.update(
                movieId = item.id,
                favourite = item.favourite,
                wannaWatchIt = true
            )
            val result = dao.findById(id = item.id)

            // Then
            assertNotNull(result)
            result?.let {
                assertEquals(item.id, it.id)
                assertTrue(it.favourite)
                assertTrue(it.wannaWatchIt)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun findFavouriteMovies() {
        runBlocking {
            // Given
            val expected1 = MovieStatus(
                id = 1,
                title = "title1",
                backdropPath = "backdropPath1",
                favourite = true
            )
            val expected2 = MovieStatus(
                id = 2,
                title = "title2",
                backdropPath = "backdropPath2",
                favourite = true
            )

            // When
            dao.insert(expected1)
            dao.insert(expected2)
            val result = dao.findFavourites()

            // Then
            assertEquals(2, result.size)
            assertEquals(expected1.id, result.first().id)
            assertTrue(result.first().favourite)
            assertEquals(expected2.id, result.last().id)
            assertTrue(result.last().favourite)
        }
    }

    @Test
    @Throws(Exception::class)
    fun findFavouriteMoviesAndGetEmptyList() {
        runBlocking {
            // When
            val result = dao.findFavourites()

            // Then
            assertTrue(result.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun findWannaWatchItMovies() {
        runBlocking {
            // Given
            val expected1 = MovieStatus(
                id = 1,
                title = "title1",
                backdropPath = "backdropPath1",
                wannaWatchIt = true
            )
            val expected2 = MovieStatus(
                id = 2,
                title = "title2",
                backdropPath = "backdropPath2",
                wannaWatchIt = true
            )

            // When
            dao.insert(expected1)
            dao.insert(expected2)
            val result = dao.findMoviesToWatch()

            // Then
            assertEquals(2, result.size)
            assertEquals(expected1.id, result.first().id)
            assertTrue(result.first().wannaWatchIt)
            assertEquals(expected2.id, result.last().id)
            assertTrue(result.last().wannaWatchIt)
        }
    }

    @Test
    @Throws(Exception::class)
    fun findWannaWatchItMoviesAndGetEmptyList() {
        runBlocking {
            // When
            val result = dao.findMoviesToWatch()

            // Then
            assertTrue(result.isEmpty())
        }
    }
}
