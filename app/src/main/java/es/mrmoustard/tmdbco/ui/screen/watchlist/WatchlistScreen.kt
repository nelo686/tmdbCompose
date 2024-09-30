package es.mrmoustard.tmdbco.ui.screen.watchlist

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.ui.screen.common.TmdbItemsListScreen

@Composable
fun WatchlistScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: WatchlistViewModel = hiltViewModel<WatchlistViewModel>()
) {
    TmdbItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.movies,
        onClick = onMovieClick
    )
}
