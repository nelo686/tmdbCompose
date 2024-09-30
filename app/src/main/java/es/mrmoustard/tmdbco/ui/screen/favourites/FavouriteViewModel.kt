package es.mrmoustard.tmdbco.ui.screen.favourites

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
class FavouriteViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    var state by mutableStateOf(FavouriteViewUiState())
        private set

    init {
        viewModelScope.launch {
            state = FavouriteViewUiState(loading = true)
            state = FavouriteViewUiState(movies = repository.findFavourites())
        }
    }
}
