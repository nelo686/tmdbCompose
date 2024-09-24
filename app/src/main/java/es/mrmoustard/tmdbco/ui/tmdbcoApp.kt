package es.mrmoustard.tmdbco.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.mrmoustard.tmdbco.AppState
import es.mrmoustard.tmdbco.rememberAppState
import es.mrmoustard.tmdbco.ui.navigation.AppBottomNavigation
import es.mrmoustard.tmdbco.ui.navigation.Navigation
import es.mrmoustard.tmdbco.ui.theme.TmdbCoTheme

@Composable
fun TmdbcoApp() {
    val appState = rememberAppState()

    TmdbcoScreen {
        Scaffold(
            bottomBar = {
                if (appState.showBottomNavigation) {
                    AppBottomNavigation(
                        bottomNavOptions = AppState.BOTTOM_NAV_OPTIONS,
                        currentRoute = appState.currentRoute,
                        onNavItemClick = { appState.onNavItemClick(item = it) }
                    )
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Navigation(navController = appState.navController)
            }
        }
    }
}

@Composable
fun TmdbcoScreen(content: @Composable () -> Unit) {
    TmdbCoTheme {
        content()
    }
}
