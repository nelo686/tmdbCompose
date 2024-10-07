package es.mrmoustard.tmdbco.ui.screen.toprated

import app.cash.turbine.test
import arrow.core.right
import es.mrmoustard.tmdbco.mock.topRatedWrapperMock
import es.mrmoustard.tmdbco.ui.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class TopRatedViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `test TopRatedUiState loading state`() =
        runTest {
            val results = MutableStateFlow(TopRatedUiState(loading = true))
            results.test {
                assertEquals(TopRatedUiState(loading = true), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `test TopRatedUiState movie state`() =
        runTest {
            val results = MutableStateFlow(TopRatedUiState(loading = true))
            results.test {
                results.value = TopRatedUiState(movies = topRatedWrapperMock.results.right())

                assertEquals(TopRatedUiState(loading = true), awaitItem())
                assertEquals(TopRatedUiState(movies = topRatedWrapperMock.results.right()), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
}
