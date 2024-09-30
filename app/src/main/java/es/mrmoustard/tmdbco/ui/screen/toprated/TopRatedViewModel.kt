package es.mrmoustard.tmdbco.ui.screen.toprated

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import dagger.hilt.android.lifecycle.HiltViewModel
import es.mrmoustard.data.repository.MoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    var state by mutableStateOf(TopRatedUiState())
        private set

    init {
        viewModelScope.launch {
            state = TopRatedUiState(loading = true)
            repository.getTopRated(page = 1).getOrNull()?.let {
                state = TopRatedUiState(movies = it.results.right())
            }
        }
    }
}