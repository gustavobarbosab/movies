package io.github.gustavobarbosab.showcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.core.result.ResultHandler
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewState.Action.*

class ShowCaseViewModel(private val useCase: ShowCaseUseCase) : ViewModel(), ResultHandler {

    val states = ShowCaseViewState()

    init {
        getPopularMovies()
        getLatestMovies()
        getBannerMovies()
        getTopRatedMovies()
    }

    fun onSearchMovie() {
        states.action.value = RedirectToSearch
    }

    private fun getBannerMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowBannerLoading
            handleResult(
                result = useCase.getPlayingNow(),
                onSuccess = states.bannerMovies::setValue,
                onError = { states.action.value = ErrorLoadBanner }
            )
            states.action.value = HideBannerLoading
        }
    }

    fun getPopularMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowPopularLoading
            handleResult(
                result = useCase.getPopularMovies(),
                onSuccess = states.popularMovies::setValue,
                onError = { states.action.value = ErrorLoadPopular }
            )
        }
    }

    fun getLatestMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowLatestLoading
            handleResult(
                result = useCase.getLatestMovies(),
                onSuccess = states.latestMovies::setValue,
                onError = { states.action.value = ErrorLoadLatest }
            )
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowTopRatedLoading
            handleResult(
                result = useCase.getTopRatedMovies(),
                onSuccess = states.topRatedMovies::setValue,
                onError = { states.action.value = ErrorLoadTopRated }
            )
        }
    }
}