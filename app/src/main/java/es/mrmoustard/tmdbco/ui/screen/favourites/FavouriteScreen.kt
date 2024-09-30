package es.mrmoustard.tmdbco.ui.screen.favourites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.ui.screen.common.TmdbItemsListScreen

@Composable
fun FavouriteScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: FavouriteViewModel = hiltViewModel<FavouriteViewModel>()
) {
    TmdbItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.movies,
        onClick = onMovieClick
    )
}
