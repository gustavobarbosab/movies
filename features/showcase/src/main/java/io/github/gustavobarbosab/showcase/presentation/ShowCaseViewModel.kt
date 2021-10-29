package io.github.gustavobarbosab.showcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.core.domain.onSuccess
import io.github.gustavobarbosab.core.domain.orError
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewState.Action.*

class ShowCaseViewModel(private val useCase: ShowCaseUseCase) : ViewModel() {

    val states = ShowCaseViewState()

    init {
        getPopularMovies()
        getLatestMovies()
        getPlayingNowMovies()
        getTopRatedMovies()
    }

    fun onSearchMovie() {
        states.action.value = RedirectToSearch
    }

    private fun getPlayingNowMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowBannerLoading

            useCase.getPlayingNow()
                .onSuccess(states.bannerMovies::setValue)
                .orError { }

            states.action.value = HideBannerLoading
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowPopularLoading

            useCase.getPopularMovies()
                .onSuccess(states.popularMovies::setValue)

            states.action.value = HidePopularLoading
        }
    }

    private fun getLatestMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowLatestLoading

            useCase.getLatestMovies()
                .onSuccess(states.latestMovies::setValue)
                .orError { }

            states.action.value = HideLatestLoading
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launchMain {
            states.action.value = ShowTopRatedLoading

            useCase.getTopRatedMovies()
                .onSuccess(states.topRatedMovies::setValue)
                .orError { }

            states.action.value = HideTopRatedLoading
        }
    }
}