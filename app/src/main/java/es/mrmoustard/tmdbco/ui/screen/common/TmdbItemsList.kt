package es.mrmoustard.tmdbco.ui.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.R

@Composable
fun TmdbItemsList(
    loading: Boolean,
    items: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (loading) {
            CircularProgressIndicator()
        }

        if (items.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(dimensionResource(id = R.dimen.grid_cell_width)),
                contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_small))
            ) {
                items(items) {
                    TmdbListItem(
                        item = it,
                        onClick = { onMovieClick(it) }
                    )
                }
            }
        } else {
            EmptyListView(modifier = modifier)
        }
    }
}
