package es.mrmoustard.domain.model

data class TopRatedWrapper(
    val page: Int = 0,
    val results: List<Movie> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
