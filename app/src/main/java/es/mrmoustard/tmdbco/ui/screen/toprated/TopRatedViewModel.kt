package es.mrmoustard.tmdbco.ui.screen.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import dagger.hilt.android.lifecycle.HiltViewModel
import es.mrmoustard.data.repository.MoviesRepository
import es.mrmoustard.data.source.dto.Result
import es.mrmoustard.domain.model.TopRatedWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(movies = repository.getTopRated(page = 1))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: Result<TopRatedWrapper> = TopRatedWrapper().right()
    )
}