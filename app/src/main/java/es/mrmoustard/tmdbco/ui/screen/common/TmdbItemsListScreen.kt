package es.mrmoustard.tmdbco.ui.screen.common

import androidx.compose.runtime.Composable
import es.mrmoustard.data.source.remote.dto.Result
import es.mrmoustard.domain.model.Movie

@Composable
fun TmdbItemsListScreen(
    loading: Boolean,
    items: Result<List<Movie>>,
    onClick: (Movie) -> Unit
) {
    items.fold(
        { ErrorMessage(error = it) },
        {
            TmdbItemsList(
                loading = loading,
                items = it,
                onMovieClick = onClick
            )
        }
    )
}
