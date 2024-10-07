package es.mrmoustard.tmdbco.ui.screen.toprated

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.ui.screen.common.TmdbItemsListScreen

@Composable
fun TopRatedScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: TopRatedViewModel = hiltViewModel<TopRatedViewModel>()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    TmdbItemsListScreen(
        loading = state.loading,
        items = state.movies,
        onClick = onMovieClick
    )
}
