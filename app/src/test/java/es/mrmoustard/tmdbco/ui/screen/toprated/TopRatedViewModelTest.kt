package es.mrmoustard.tmdbco.ui.screen.toprated

import arrow.core.left
import arrow.core.right
import es.mrmoustard.data.repository.MoviesRepository
import es.mrmoustard.data.source.remote.dto.Error
import es.mrmoustard.domain.model.TopRatedWrapper
import es.mrmoustard.tmdbco.ui.MainDispatcherRule
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TopRatedViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TopRatedViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `GIVEN a page number, WHEN call getTopRated(), THEN a movie list is retrieved`() =
        runBlocking {
            // Given
            val repository = mock<MoviesRepository>()
            whenever(repository.getTopRated(any(), any())).thenReturn(TopRatedWrapper().right())

            // When
            viewModel = TopRatedViewModel(repository)

            // Then
            assertTrue(viewModel.state.movies.isRight())
            assertThat(viewModel.state.movies.getOrNull(), instanceOf(TopRatedWrapper::class.java))
        }

    @Test
    fun `GIVEN a page number, WHEN call getTopRated(), but THEN a unexpected error is retrieved`() =
        runBlocking {
            // Given
            val repository = mock<MoviesRepository>()
            whenever(repository.getTopRated(any(), any())).thenReturn(Error.Unknown("unknown").left())

            // When
            viewModel = TopRatedViewModel(repository)

            // Then
            assertTrue(viewModel.state.movies.isLeft())
            assertThat(viewModel.state.movies.leftOrNull(), instanceOf(Error::class.java))
        }
}