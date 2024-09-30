package es.mrmoustard.tmdbco.ui.screen.watchlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.mrmoustard.data.repository.MoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    var state by mutableStateOf(WatchlistUiState())
        private set

    init {
        viewModelScope.launch {
            state = WatchlistUiState(loading = true)
            state = WatchlistUiState(movies = repository.findMoviesToWatch())
        }
    }
}