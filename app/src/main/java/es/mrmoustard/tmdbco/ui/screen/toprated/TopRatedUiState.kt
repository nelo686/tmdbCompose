package es.mrmoustard.tmdbco.ui.screen.toprated

import arrow.core.right
import es.mrmoustard.data.source.remote.dto.Result
import es.mrmoustard.domain.model.TopRatedWrapper

data class TopRatedUiState(
    val loading: Boolean = false,
    val movies: Result<TopRatedWrapper> = TopRatedWrapper().right()
)
