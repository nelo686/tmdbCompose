package es.mrmoustard.tmdbco.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import dagger.hilt.android.lifecycle.HiltViewModel
import es.mrmoustard.data.repository.MoviesRepository
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import es.mrmoustard.tmdbco.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MoviesRepository
): ViewModel() {
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private var _uiState = MutableStateFlow(MovieDetailUiState(loading = true))
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { MovieDetailUiState(loading = true) }
            _uiState.update {
                MovieDetailUiState(
                    movie = repository.getMovieDetails(movieId = id)
                )
            }
        }
    }

    fun onStatusChange(status: MovieStatus) {
        viewModelScope.launch {
            repository.upgradeOrInsertMovieStatus(item = status)
            _uiState.update { MovieDetailUiState(
                movie = _uiState.value.movie
                    .getOrNull()
                    ?.copy(
                        favourite = status.favourite,
                        wannaWatchIt = status.wannaWatchIt
                    ).right()
            ) }
        }
    }
}
