package es.mrmoustard.tmdbco.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import arrow.core.left
import arrow.core.right
import es.mrmoustard.data.repository.MoviesRepository
import es.mrmoustard.data.source.remote.dto.Error
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.domain.model.TopRatedWrapper
import es.mrmoustard.tmdbco.ui.MainDispatcherRule
import es.mrmoustard.tmdbco.ui.screen.toprated.TopRatedViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MovieDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `GIVEN a movie id, WHEN call getMovieDetails(), THEN movie details are retrieved`() =
        runBlocking {
            // Given
            val savedStateHandle = mock<SavedStateHandle>()
            val repository = mock<MoviesRepository>()
            whenever(repository.getMovieDetails(any())).thenReturn(MovieDetail().right())

            // When
            viewModel = MovieDetailViewModel(savedStateHandle, repository)

            // Then
            assertTrue(viewModel.state.movie.isRight())
            assertThat(viewModel.state.movie.getOrNull(), instanceOf(MovieDetail::class.java))
        }

    @Test
    fun `GIVEN a movie id, WHEN call getMovieDetails(), but THEN a unexpected error is retrieved`() =
        runBlocking {
            // Given
            val savedStateHandle = mock<SavedStateHandle>()
            val repository = mock<MoviesRepository>()
            whenever(repository.getMovieDetails(any())).thenReturn(Error.Unknown("unknown").left())

            // When
            viewModel = MovieDetailViewModel(savedStateHandle, repository)

            // Then
            assertTrue(viewModel.state.movie.isLeft())
            assertThat(viewModel.state.movie.leftOrNull(), instanceOf(Error::class.java))
        }
}