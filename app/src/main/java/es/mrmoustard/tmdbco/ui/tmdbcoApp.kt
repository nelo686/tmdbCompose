package es.mrmoustard.tmdbco.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.mrmoustard.tmdbco.ui.navigation.AppBottomNavigation
import es.mrmoustard.tmdbco.ui.navigation.NavItem
import es.mrmoustard.tmdbco.ui.navigation.Navigation
import es.mrmoustard.tmdbco.ui.navigation.navigatePoppingUpToStartDestination
import es.mrmoustard.tmdbco.ui.theme.TmdbCoTheme

@Composable
fun TmdbcoApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    TmdbcoScreen {
        Scaffold(
            bottomBar = {
                AppBottomNavigation(currentRoute = currentRoute) { navItem ->
                    navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Navigation(navController = navController)
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
