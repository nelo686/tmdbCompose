package es.mrmoustard.tmdbco.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MovieDetailScreen(
    movieId: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Movie Details ${movieId}",
            style = MaterialTheme
                .typography.titleLarge,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace
        )
    }
}