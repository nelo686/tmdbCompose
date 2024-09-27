package es.mrmoustard.tmdbco.ui.screen.detail

import arrow.core.right
import es.mrmoustard.data.source.remote.dto.Result
import es.mrmoustard.domain.model.MovieDetail

data class MovieDetailUiState(
    val loading: Boolean = false,
    val movie: Result<MovieDetail?> = null.right()
)
