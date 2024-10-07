package es.mrmoustard.tmdbco.ui.screen.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import dagger.hilt.android.lifecycle.HiltViewModel
import es.mrmoustard.data.repository.MoviesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    val uiState: StateFlow<TopRatedUiState> = flow {
        repository.getTopRated(page = 1).getOrNull()?.let {
            emit(TopRatedUiState(movies = it.results.right()))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TopRatedUiState(loading = true)
    )
}
