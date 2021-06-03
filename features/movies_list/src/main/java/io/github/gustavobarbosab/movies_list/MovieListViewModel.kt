package io.github.gustavobarbosab.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.core.domain.data
import io.github.gustavobarbosab.core.domain.isSuccess
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(
    val useCase: MovieUseCase
) : ViewModel() {

    val movieResponse = MutableLiveData<List<Movie>>()
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