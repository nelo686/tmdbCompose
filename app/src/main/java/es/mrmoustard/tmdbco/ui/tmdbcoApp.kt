package es.mrmoustard.tmdbco.ui

import androidx.compose.runtime.Composable
import es.mrmoustard.tmdbco.ui.theme.TmdbCoTheme

@Composable
fun TmdbcoApp(content: @Composable () -> Unit) {
    TmdbCoTheme {
        content()
    }
}