package es.mrmoustard.tmdbco.ui.screen.toprated

import arrow.core.right
import es.mrmoustard.data.source.remote.dto.Result
import es.mrmoustard.domain.model.Movie

data class TopRatedUiState(
    val loading: Boolean = false,
    val movies: Result<List<Movie>> = emptyList<Movie>().right()
)
