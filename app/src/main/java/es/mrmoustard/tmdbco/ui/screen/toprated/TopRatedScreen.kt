package es.mrmoustard.tmdbco.ui.screen.toprated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.ui.screen.common.TmdbItemsListScreen

@Composable
fun TopRatedScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: TopRatedViewModel = hiltViewModel<TopRatedViewModel>()
) {
    TmdbItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.movies,
        onClick = onMovieClick
    )
}
