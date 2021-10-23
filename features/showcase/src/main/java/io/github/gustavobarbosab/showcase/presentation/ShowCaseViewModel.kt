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

    init {
        getPopularMovies()
        getLatestMovies()
        getPlayingNowMovies()
        getTopRatedMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.PopularShowLoading

            val response = useCase.getPopularMovies()

            response.data()?.let {
                popularMovieResponse.postValue(it)
            }

            loading.value = MovieListState.PopularHideLoading
        }
    }

    private fun getLatestMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.LatestShowLoading

            val response = useCase.getLatestMovies()

            response.data()?.let {
                latestMovieResponse.postValue(it)
            }

            loading.value = MovieListState.LatestShowLoading
        }
    }

    private fun getPlayingNowMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.BannerShowLoading

            val response = useCase.getPlayingNow()

            response.data()?.let {
                playingNowResponse.postValue(it)
            }

            loading.value = MovieListState.BannerHideLoading
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.TopRatedShowLoading

            val response = useCase.getTopRatedMovies()

            response.data()?.let {
                topRatedResponse.postValue(it)
            }

            loading.value = MovieListState.TopRatedHideLoading
        }
    }

    sealed class MovieListState {
        object TopRatedShowLoading : MovieListState()
        object TopRatedHideLoading : MovieListState()
        object PopularShowLoading : MovieListState()
        object PopularHideLoading : MovieListState()
        object BannerShowLoading : MovieListState()
        object BannerHideLoading : MovieListState()
        object LatestShowLoading : MovieListState()
        object LatestHideLoading : MovieListState()
    }

    sealed class MovieListAction {
        // TODO
    }
}