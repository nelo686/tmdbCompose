package es.mrmoustard.tmdbco.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    internal val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        // baseRoute/{arg1}/{arg2}
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(name = it.key) { type = it.type }
    }

    object TopRated : NavItem(baseRoute = "top_rated")
}

enum class NavArg(val key: String, val type: NavType<*>) {
    ItemId(key = "itemId", type = NavType.IntType)
}