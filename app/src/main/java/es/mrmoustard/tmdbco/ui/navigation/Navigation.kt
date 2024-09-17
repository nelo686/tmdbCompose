package es.mrmoustard.tmdbco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import es.mrmoustard.tmdbco.ui.navigation.NavCommand.ContentDetail
import es.mrmoustard.tmdbco.ui.navigation.NavItem.FAVOURITE
import es.mrmoustard.tmdbco.ui.navigation.NavItem.TOP_RATED
import es.mrmoustard.tmdbco.ui.navigation.NavItem.WATCHLIST
import es.mrmoustard.tmdbco.ui.screen.detail.MovieDetailScreen
import es.mrmoustard.tmdbco.ui.screen.favourites.FavouriteScreen
import es.mrmoustard.tmdbco.ui.screen.toprated.TopRatedScreen
import es.mrmoustard.tmdbco.ui.screen.watchlist.WatchlistScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.TOP_RATED.route
    ) {
        topRatedNav(navController = navController)
        favouriteNav(navController = navController)
        watchlistNav(navController = navController)
    }
}

private fun NavGraphBuilder.topRatedNav(navController: NavHostController) {
    navigation(
        route = Feature.TOP_RATED.route,
        startDestination = NavCommand.ContentType(Feature.TOP_RATED).route
    ) {
        composable(navCommand = TOP_RATED.navCommand) {
            TopRatedScreen(
                onMovieClick = { movie ->
                    navController.navigate(
                        route = ContentDetail(Feature.TOP_RATED).buildRoute(movie.id)
                    )
                }
            )
        }
        composable(navCommand = ContentDetail(Feature.TOP_RATED)) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.findArg(arg = NavArg.ItemId))
        }
    }
}

private fun NavGraphBuilder.favouriteNav(navController: NavHostController) {
    navigation(
        route = Feature.FAVOURITE.route,
        startDestination = NavCommand.ContentType(Feature.FAVOURITE).route
    ) {
        composable(navCommand = FAVOURITE.navCommand) {
            FavouriteScreen { movie ->
                navController.navigate(
                    route = ContentDetail(Feature.FAVOURITE).buildRoute(movie.id)
                )
            }
        }
        composable(navCommand = ContentDetail(Feature.FAVOURITE)) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.findArg(arg = NavArg.ItemId))
        }
    }
}

private fun NavGraphBuilder.watchlistNav(navController: NavHostController) {
    navigation(
        route = Feature.WATCHLIST.route,
        startDestination = NavCommand.ContentType(Feature.WATCHLIST).route
    ) {
        composable(navCommand = WATCHLIST.navCommand) {
            WatchlistScreen { movie ->
                navController.navigate(
                    route = ContentDetail(Feature.WATCHLIST).buildRoute(movie.id)
                )
            }
        }
        composable(navCommand = ContentDetail(Feature.WATCHLIST)) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.findArg(arg = NavArg.ItemId))
        }
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

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}