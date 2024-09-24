package es.mrmoustard.tmdbco.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import es.mrmoustard.domain.model.MovieDetail

@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel<MovieDetailViewModel>()) {
    val state = viewModel.state

    if (state.loading) {
        CircularProgressIndicator()
    }

    if (state.movie.getOrNull() != null) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Header(state.movie.getOrNull()!!)
            }

            section(title = "", content = "")
            section(title = "", content = "")
        }
    }
}

@Composable
fun Header(movie: MovieDetail) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w342${movie.backdropPath}",
                contentDescription =movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
    }
}

fun LazyListScope.section(title: String, content: String) {
    item {
        Text(text = title)
        Text(text = content)
    }
}
