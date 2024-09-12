package es.mrmoustard.tmdbco.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.mrmoustard.tmdbco.model.Movie
import es.mrmoustard.tmdbco.ui.screen.common.TmdbItemsList

@Composable
fun TopRatedScreen(onMovieClick: (Movie) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize())
    { innerPadding ->
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
            onMovieClick = { onMovieClick(it) },
            modifier =  Modifier.padding(innerPadding)
        )
    }
}
