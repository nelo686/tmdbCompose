package es.mrmoustard.tmdbco.ui.screen.toprated

import androidx.compose.runtime.Composable
import es.mrmoustard.tmdbco.model.Movie
import es.mrmoustard.tmdbco.ui.screen.common.TmdbItemsList

@Composable
fun TopRatedScreen(onMovieClick: (Movie) -> Unit) {
    val movies = (1..10).map {
        Movie(
            backdropPath = "https://picsum.photos/id/$it/200/300?grayscale&blur=2",
            id = it,
            title = "Title $it"
        )
    }
    TmdbItemsList(
        loading = false,
        items = movies,
        onMovieClick = { onMovieClick(it) }
    )
}
