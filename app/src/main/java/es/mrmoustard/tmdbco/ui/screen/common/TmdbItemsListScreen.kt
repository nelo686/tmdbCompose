package es.mrmoustard.tmdbco.ui.screen.common

import androidx.compose.runtime.Composable
import es.mrmoustard.data.source.dto.Result
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.domain.model.TopRatedWrapper

@Composable
fun TmdbItemsListScreen(
    loading: Boolean,
    items: Result<TopRatedWrapper>,
    onClick: (Movie) -> Unit
) {
    items.fold(
        { ErrorMessage(error = it) },
        {
            TmdbItemsList(
                loading = loading,
                items = it.results,
                onMovieClick = onClick
            )
        }
    )
}
