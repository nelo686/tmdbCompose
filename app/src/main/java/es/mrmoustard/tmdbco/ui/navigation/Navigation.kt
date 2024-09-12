package es.mrmoustard.tmdbco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.mrmoustard.tmdbco.ui.screen.home.TopRatedScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavItem.TopRated.route
    ) {
        composable(navItem = NavItem.TopRated) {
            TopRatedScreen { movie ->
                navController.navigate(route = "movieDetail")
            }
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}