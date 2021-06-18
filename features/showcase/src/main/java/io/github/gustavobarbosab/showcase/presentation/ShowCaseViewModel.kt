package io.github.gustavobarbosab.showcase.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.core.domain.data
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowCaseViewModel(
    private val useCase: ShowCaseUseCase
) : ViewModel() {

    val movieResponse = MutableLiveData<List<MovieShowCase>>()
    val loading = MutableLiveData<MovieListState>()

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.postValue(MovieListState.ShowLoading)

            val response = useCase.getPopularMovies()

            response.data()?.let {
                movieResponse.postValue(it)
            }

            loading.value = MovieListState.HideLoading
        }
    }

    sealed class MovieListState {
        object ShowLoading : MovieListState()
        object HideLoading : MovieListState()
    }

    sealed class MovieListAction {
        // TODO
    }
}