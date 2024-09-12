package es.mrmoustard.tmdbco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.mrmoustard.tmdbco.ui.screen.detail.MovieDetailScreen
import es.mrmoustard.tmdbco.ui.screen.favourites.FavouriteScreen
import es.mrmoustard.tmdbco.ui.screen.home.TopRatedScreen
import es.mrmoustard.tmdbco.ui.screen.watchlist.WatchlistScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavItem.TopRated.route
    ) {
        composable(NavItem.Favourite) {
            FavouriteScreen { movie ->
                navController.navigateToDetail(movie.id)
            }
        }
        composable(NavItem.TopRated) {
            TopRatedScreen { movie ->
                navController.navigateToDetail(movie.id)
            }
        }
        composable(NavItem.Watchlist) {
            WatchlistScreen { movie ->
                navController.navigateToDetail(movie.id)
            }
        }

        composable(NavItem.Detail) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.findArg(arg = NavArg.ItemId))
        }
    }
}

private fun NavHostController.navigateToDetail(itemId: Int) {
    navigate(route = NavItem.Detail.createNavRoute(itemId))
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