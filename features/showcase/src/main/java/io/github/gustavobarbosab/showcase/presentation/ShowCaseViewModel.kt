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

    val popularMovieResponse = MutableLiveData<List<MovieShowCase>>()
    val topRatedResponse = MutableLiveData<List<MovieShowCase>>()
    val playingNowResponse = MutableLiveData<List<MovieShowCase>>()
    val latestMovieResponse = MutableLiveData<List<MovieShowCase>>()
    val loading = MutableLiveData<MovieListState>()
    val bannerLoading = MutableLiveData<MovieListState>()

    init {
        getPopularMovies()
        getLatestMovies()
        getPlayingNowMovies()
        getTopRatedMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.ShowLoading

            val response = useCase.getPopularMovies()

            response.data()?.let {
                popularMovieResponse.postValue(it)
            }

            loading.value = MovieListState.HideLoading
        }
    }

    private fun getLatestMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.ShowLoading

            val response = useCase.getLatestMovies()

            response.data()?.let {
                latestMovieResponse.postValue(it)
            }

            loading.value = MovieListState.HideLoading
        }
    }

    private fun getPlayingNowMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            bannerLoading.value = MovieListState.ShowLoading

            val response = useCase.getPlayingNow()

            response.data()?.let {
                playingNowResponse.postValue(it)
            }

            bannerLoading.value = MovieListState.HideLoading
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.ShowLoading

            val response = useCase.getTopRatedMovies()

            response.data()?.let {
                topRatedResponse.postValue(it)
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