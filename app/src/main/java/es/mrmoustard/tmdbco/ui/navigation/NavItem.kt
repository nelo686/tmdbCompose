package es.mrmoustard.tmdbco.ui.navigation

import  androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import es.mrmoustard.tmdbco.R
import es.mrmoustard.tmdbco.ui.navigation.NavCommand.ContentType

enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    TOP_RATED(
        navCommand = ContentType(Feature.TOP_RATED),
        icon = Icons.Outlined.Home,
        title = R.string.top_rated
    ),
    FAVOURITE(
        navCommand = ContentType(Feature.FAVOURITE),
        icon = Icons.Outlined.FavoriteBorder,
        title = R.string.favourites
    ),
    WATCHLIST(
        navCommand = ContentType(Feature.WATCHLIST),
        icon = Icons.Outlined.Movie,
        title = R.string.watchlist
    )
}

sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {
    class ContentType(feature: Feature) : NavCommand(feature = feature)
    class ContentDetail(feature: Feature) : NavCommand(
        feature = feature,
        subRoute = "detail",
        navArgs = listOf(NavArg.ItemId)
    ) {
        fun buildRoute(itemId: Int) = "${feature.route}/$subRoute/$itemId"
    }

    val route = run {
        // route/subRoute/{arg1}/{arg2}
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(feature.route, subRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(name = it.key) { type = it.type }
    }
}

enum class NavArg(val key: String, val type: NavType<*>) {
    ItemId(key = "itemId", type = NavType.IntType)
}