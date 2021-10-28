package io.github.gustavobarbosab.showcase.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.core.domain.orError
import io.github.gustavobarbosab.core.domain.onSuccess
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

            response.onSuccess {
                popularMovieResponse.postValue(it)
            }

            loading.value = MovieListState.PopularHideLoading
        }
    }

    private fun getLatestMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.LatestShowLoading

            useCase.getLatestMovies()
                .onSuccess(latestMovieResponse::postValue)
                .orError { }

            loading.value = MovieListState.LatestShowLoading
        }
    }

    private fun getPlayingNowMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.BannerShowLoading

            useCase.getPlayingNow()
                .onSuccess(playingNowResponse::postValue)
                .orError { }

            loading.value = MovieListState.BannerHideLoading
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            loading.value = MovieListState.TopRatedShowLoading

            useCase.getTopRatedMovies()
                .onSuccess(topRatedResponse::postValue)
                .orError { }

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
        object CallSearchScreen : MovieListAction()
    }
}