package es.mrmoustard.data.source.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.mrmoustard.data.mock.movieStatusMock
import es.mrmoustard.data.source.local.database.MovieDao
import es.mrmoustard.data.source.local.database.MoviesDatabase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MoviesLocalDataSourceImplTest {

    private lateinit var db: MoviesDatabase
    private lateinit var dao: MovieDao
    private lateinit var dataSource: MoviesLocalDataSource

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = db.movieDao()
        dataSource = MoviesLocalDataSourceImpl(db)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun Given_an_existing_item_When_call_findById_Then_return_the_item() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock)

            // When
            val result = dataSource.findById(movieStatusMock.id)

            // Then
            assertNotNull(result)
            assertEquals(movieStatusMock.id, result!!.id)
        }
    }

    @Test
    fun Given_a_non_existing_item_When_call_findById_Then_return_null() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock)

            // When
            val result = dataSource.findById(100)

            // Then
            assertNull(result)
        }
    }

    @Test
    fun Given_an_item_When_call_insert_Then_item_is_stored() {
        runBlocking {
            // When
            dao.insert(movieStatusMock)

            // Then
            val result = dataSource.findById(movieStatusMock.id)
            assertNotNull(result)
            assertEquals(movieStatusMock.id, result!!.id)
        }
    }

    @Test
    fun Given_an_item_When_call_delete_Then_item_is_deleted() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock)

            // When
            dao.delete(movieStatusMock)

            // Then
            val result = dataSource.findById(movieStatusMock.id)
            assertNull(result)
        }
    }

    @Test
    fun Given_an_item_When_call_update_Then_item_is_updated() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock)

            // When
            dao.update(
                movieId = movieStatusMock.id,
                favourite = movieStatusMock.favourite,
                wannaWatchIt = false
            )

            // Then
            val result = dataSource.findById(movieStatusMock.id)
            assertNotNull(result)
            assertEquals(movieStatusMock.id, result!!.id)
            assertEquals(movieStatusMock.favourite, result.favourite)
            assertEquals(false, result.wannaWatchIt)
        }
    }

    @Test
    fun Given_a_favourite_item_When_call_findFavourites_Then_item_is_retrieved() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock)

            // When
            val result = dataSource.findFavourites()

            // Then
            assertTrue(result.isNotEmpty())
            assertEquals(movieStatusMock.id, result.first().id)
        }
    }

    @Test
    fun Given_a_non_favourite_item_When_call_findFavourites_Then_an_wmpty_list_is_retrieved() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock.copy(favourite = false))

            // When
            val result = dataSource.findFavourites()

            // Then
            assertTrue(result.isEmpty())
        }
    }

    @Test
    fun Given_a_favourite_item_When_call_findMoviesToWatch_Then_item_is_retrieved() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock)

            // When
            val result = dataSource.findMoviesToWatch()

            // Then
            assertTrue(result.isNotEmpty())
            assertEquals(movieStatusMock.id, result.first().id)
        }
    }

    @Test
    fun Given_a_non_favourite_item_When_call_findMoviesToWatch_Then_an_wmpty_list_is_retrieved() {
        runBlocking {
            // Given
            dao.insert(movieStatusMock.copy(wannaWatchIt = false))

            // When
            val result = dataSource.findMoviesToWatch()

            // Then
            assertTrue(result.isEmpty())
        }
    }
}