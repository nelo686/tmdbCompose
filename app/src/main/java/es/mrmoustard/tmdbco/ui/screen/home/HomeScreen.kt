package es.mrmoustard.tmdbco.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import es.mrmoustard.tmdbco.ui.screen.common.AppBarIcon

@Composable
fun HomeScreen(
    onBottomBarClick: () -> Unit,
    modifier: Modifier = Modifier) {
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.ThumbUp)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                AppBarIcon(
                    imageVector = Icons.Default.Favorite,
                    onClick = { /*TODO*/ },
                    contentDescription = "Favourites"
                )
                AppBarIcon(
                    imageVector = Icons.Default.ThumbUp,
                    onClick = {
                        selected.value = Icons.Default.ThumbUp

                    },
                    contentDescription = "Top Rated"
                )
                AppBarIcon(
                    imageVector = Icons.Default.PlayArrow,
                    onClick = { /*TODO*/ },
                    contentDescription = "Watchlist"
                )
            }
        }
    ) { padding ->
        TopRatedScreen(
            onMovieClick = {},
            modifier = Modifier.padding(padding)
        )
    }
}