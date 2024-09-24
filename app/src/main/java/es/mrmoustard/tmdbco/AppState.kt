package es.mrmoustard.tmdbco

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.mrmoustard.tmdbco.ui.navigation.NavItem
import es.mrmoustard.tmdbco.ui.navigation.navigatePoppingUpToStartDestination

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState = remember(navController) {
    AppState(navController)
}

class AppState(
    val navController: NavHostController
) {
    companion object {
        val BOTTOM_NAV_OPTIONS = NavItem.entries
    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    val showBottomNavigation: Boolean
        @Composable get() = BOTTOM_NAV_OPTIONS.any { currentRoute.contains(it.navCommand.subRoute) }

    fun onNavItemClick(item: NavItem) {
        navController.navigatePoppingUpToStartDestination(item.navCommand.route)
    }
}