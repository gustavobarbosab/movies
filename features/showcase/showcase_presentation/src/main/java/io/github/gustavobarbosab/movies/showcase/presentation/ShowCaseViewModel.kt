package io.github.gustavobarbosab.movies.showcase.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseViewState.Action.*
import io.gustavobarbosab.suspendresult.CoroutineResultHandler

class ShowCaseViewModel(
    private val useCase: ShowCaseUseCase
) : BaseViewModel<ShowCaseViewState>(), CoroutineResultHandler {

    override val state = ShowCaseViewState()

    init {
        getPopularMovies()
        getLatestMovies()
        getBannerMovies()
        getTopRatedMovies()
    }

    fun onSearchMovie() {
        state.action.value = RedirectToSearch
    }

    fun showDetails(movie: MovieShowCase) {
        // TODO extrair para mapper
        val movieSelected = MovieDetailDomain(
            movie.id,
            movie.name,
            movie.description,
            movie.imageUrl,
            movie.posterUrl
        )
        state.action.value = ShowMovieDetails(movieSelected)
    }

    fun getBannerMovies() {
        viewModelScope.launchMain {
            handleResult(
                result = useCase.getPlayingNow(),
                onSuccess = state.bannerMovies::setValue,
                onError = { state.action.value = ErrorLoadBanner }
            )
        }
    }

    fun getPopularMovies() {
        viewModelScope.launchMain {
            state.action.value = ShowPopularLoading
            handleResult(
                result = useCase.getPopularMovies(),
                onSuccess = state.popularMovies::setValue,
                onError = { state.action.value = ErrorLoadPopular }
            )
        }
    }

    fun getLatestMovies() {
        viewModelScope.launchMain {
            state.action.value = ShowLatestLoading
            handleResult(
                result = useCase.getLatestMovies(),
                onSuccess = state.latestMovies::setValue,
                onError = { state.action.value = ErrorLoadLatest }
            )
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launchMain {
            state.action.value = ShowTopRatedLoading
            handleResult(
                result = useCase.getTopRatedMovies(),
                onSuccess = state.topRatedMovies::setValue,
                onError = { state.action.value = ErrorLoadTopRated }
            )
        }
    }
}