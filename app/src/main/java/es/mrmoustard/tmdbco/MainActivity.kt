package es.mrmoustard.tmdbco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import es.mrmoustard.tmdbco.model.Movie
import es.mrmoustard.tmdbco.ui.TmdbcoApp
import es.mrmoustard.tmdbco.ui.screen.common.TmdbItemsList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmdbcoApp {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                        onMovieClick = {},
                        modifier =  Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

