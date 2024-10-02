package es.mrmoustard.tmdbco.ui.screen.toprated

import arrow.core.right
import es.mrmoustard.data.repository.MoviesRepository
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.mock.movieMock
import es.mrmoustard.tmdbco.mock.topRatedWrapperMock
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
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TopRatedViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock lateinit var repository: MoviesRepository

    private lateinit var viewModel: TopRatedViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `GIVEN a page number, WHEN call getTopRated(), THEN a movie list is retrieved`() =
        runBlocking {
            // Given
            whenever(repository.getTopRated(any(), any())).thenReturn(topRatedWrapperMock.right())

            // When
            viewModel = TopRatedViewModel(repository)

            // Then
            assertTrue(viewModel.state.movies.isRight())
            assertThat(viewModel.state.movies.getOrNull(), hasItems(movieMock))
            assertThat(viewModel.state.movies.getOrNull()?.first(), instanceOf(Movie::class.java))
        }
}
