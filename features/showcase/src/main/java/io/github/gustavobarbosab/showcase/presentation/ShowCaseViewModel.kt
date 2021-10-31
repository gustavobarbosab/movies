package io.github.gustavobarbosab.showcase.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.core.result.ResultHandler
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewState.Action.*

class ShowCaseViewModel(
    private val useCase: ShowCaseUseCase
) : BaseViewModel<ShowCaseViewState>(), ResultHandler {

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