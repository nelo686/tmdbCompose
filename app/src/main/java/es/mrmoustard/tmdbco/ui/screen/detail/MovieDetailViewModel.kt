package es.mrmoustard.tmdbco.ui.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import dagger.hilt.android.lifecycle.HiltViewModel
import es.mrmoustard.data.repository.MoviesRepository
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import es.mrmoustard.tmdbco.ui.navigation.NavArg
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MoviesRepository
): ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    var state by mutableStateOf(MovieDetailUiState())
        private set

    init {
        viewModelScope.launch {
            state = MovieDetailUiState(loading = true)
            state = MovieDetailUiState(movie = repository.getMovieDetails(movieId = id))
        }
    }

    fun onStatusChange(status: MovieStatus) {
        viewModelScope.launch {
            repository.upgradeOrInsertMovieStatus(item = status)
            state = MovieDetailUiState(
                movie = state.movie
                    .getOrNull()
                    ?.copy(
                        favourite = status.favourite,
                        wannaWatchIt = status.wannaWatchIt
                    ).right()
            )
        }
    }
}
