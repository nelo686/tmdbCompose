package es.mrmoustard.tmdbco.ui.screen.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import es.mrmoustard.domain.model.Movie

@Composable
fun FavouriteScreen(onMovieClick: (Movie) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Favourite",
            style = MaterialTheme
                .typography.titleLarge,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace
        )
    }
}