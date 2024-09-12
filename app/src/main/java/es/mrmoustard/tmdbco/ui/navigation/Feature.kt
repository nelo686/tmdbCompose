package es.mrmoustard.tmdbco.ui.navigation

enum class Feature(val route: String) {
    TOP_RATED(route = "top_rated"),
    FAVOURITE(route = "favourite"),
    WATCH_LIST(route = "watchlist")
}