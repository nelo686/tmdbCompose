package es.mrmoustard.tmdbco.ui.screen.watchlist

import arrow.core.left
import arrow.core.right
import es.mrmoustard.data.repository.MoviesRepository
import es.mrmoustard.data.source.remote.dto.Error
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.mock.movieMock
import es.mrmoustard.tmdbco.ui.MainDispatcherRule
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.hasItems
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class WatchlistViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock lateinit var repository: MoviesRepository

    private lateinit var viewModel: WatchlistViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `GIVEN a movie to watch, WHEN call findMoviesToWatch(), THEN a movie list is retrieved`() =
        runBlocking {
            // Given
            val expected = movieMock.copy(wannaWatchIt = true)
            whenever(repository.findMoviesToWatch()).thenReturn(listOf(expected).right())

            // When
            viewModel = WatchlistViewModel(repository)

            // Then
            assertTrue(viewModel.state.movies.isRight())
            assertThat(viewModel.state.movies.getOrNull(), hasItems(expected))
            assertThat(viewModel.state.movies.getOrNull()?.first(), instanceOf(Movie::class.java))
        }

    @Test
    fun `GIVEN NO movie to watch, WHEN call findMoviesToWatch(), THEN a movie empty list is retrieved`() =
        runBlocking {
            // Given
            whenever(repository.findMoviesToWatch()).thenReturn(emptyList<Movie>().right())

            // When
            viewModel = WatchlistViewModel(repository)

            // Then
            assertTrue(viewModel.state.movies.isRight())
            assertTrue(viewModel.state.movies.getOrNull()!!.isEmpty())
        }

    @Test
    fun `GIVEN a movie to watch, WHEN call findMoviesToWatch(), but THEN an unexpected error is retrieved`() =
        runBlocking {
            // Given
            val expected = Error.Unknown("Unknown")
            whenever(repository.findMoviesToWatch()).thenReturn(expected.left())

            // When
            viewModel = WatchlistViewModel(repository)

            // Then
            assertTrue(viewModel.state.movies.isLeft())
            assertThat(viewModel.state.movies.leftOrNull()!!, instanceOf(Error::class.java))
        }
}
