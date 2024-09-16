package es.mrmoustard.tmdbco.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.mrmoustard.tmdbco.model.Movie
import es.mrmoustard.tmdbco.ui.screen.common.BottomNavigationBar
import es.mrmoustard.tmdbco.ui.screen.toprated.TopRatedScreen

@Composable
fun HomeScreen(
    onNavItemClick: (String) -> Unit,
    onMovieClick: (Movie) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar { featureRoute ->
                onNavItemClick(featureRoute)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TopRatedScreen(onMovieClick = { onMovieClick(it) })
        }
    }
}
