package es.mrmoustard.tmdbco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.mrmoustard.tmdbco.ui.navigation.NavCommand.ContentDetail
import es.mrmoustard.tmdbco.ui.navigation.NavItem.FAVOURITE
import es.mrmoustard.tmdbco.ui.navigation.NavItem.HOME
import es.mrmoustard.tmdbco.ui.navigation.NavItem.WATCHLIST
import es.mrmoustard.tmdbco.ui.screen.detail.MovieDetailScreen
import es.mrmoustard.tmdbco.ui.screen.favourites.FavouriteScreen
import es.mrmoustard.tmdbco.ui.screen.home.HomeScreen
import es.mrmoustard.tmdbco.ui.screen.watchlist.WatchlistScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HOME.navCommand.route
    ) {
        /** Navigate to features screen **/
        composable(navCommand = FAVOURITE.navCommand) {
            FavouriteScreen { movie ->
                navController.navigate(
                    route = ContentDetail(Feature.FAVOURITE).buildRoute(movie.id)
                )
            }
        }
        composable(navCommand = HOME.navCommand) {
            HomeScreen(
                onNavItemClick = { route ->
                    navController.loadFeature(route = route)
                },
                onMovieClick = { movie ->
                    navController.navigate(
                        route = ContentDetail(Feature.TOP_RATED).buildRoute(movie.id)
                    )
                }
            )
        }
        composable(navCommand = WATCHLIST.navCommand) {
            WatchlistScreen { movie ->
                navController.navigate(
                    route = ContentDetail(Feature.WATCHLIST).buildRoute(movie.id)
                )
            }
        }

        /** Navigate to detail screen **/
        composable(navCommand = ContentDetail(Feature.FAVOURITE)) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.findArg(arg = NavArg.ItemId))
        }
        composable(navCommand = ContentDetail(Feature.TOP_RATED)) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.findArg(arg = NavArg.ItemId))
        }
        composable(navCommand = ContentDetail(Feature.WATCHLIST)) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.findArg(arg = NavArg.ItemId))
        }

        /**
         * Funciona la navegación al detalle.
         * Funciona la navegación a las pestañas pero se pierde la barra
         */
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}

fun NavHostController.loadFeature(route: String) {
    navigate(route) { popUpTo(0) }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}