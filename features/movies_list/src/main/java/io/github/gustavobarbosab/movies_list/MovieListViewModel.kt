package io.github.gustavobarbosab.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.movies_list.data.MovieApi
import io.github.gustavobarbosab.movies_list.data.dto.PopularMovieResponse
import kotlinx.coroutines.launch

class MovieListViewModel(val movieApi: MovieApi) : ViewModel() {
    val movieResponse = MutableLiveData<List<PopularMovieResponse>>()
    val loading = MutableLiveData<MovieListState>()

    fun getPopularMovies() {
        viewModelScope.launch {
            loading.value = MovieListState.ShowLoading
            val response = movieApi.getPopularMovies()
            if (response.isSuccessful) {
                movieResponse.postValue(response.body()?.results)
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