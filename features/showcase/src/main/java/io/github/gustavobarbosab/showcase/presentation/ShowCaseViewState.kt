package io.github.gustavobarbosab.showcase.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase

class ShowCaseViewState {
    val bannerMovies: MutableLiveData<List<MovieShowCase>> = MutableLiveData()
    val latestMovies: MutableLiveData<List<MovieShowCase>> = MutableLiveData()
    val popularMovies: MutableLiveData<List<MovieShowCase>> = MutableLiveData()
    val topRatedMovies: MutableLiveData<List<MovieShowCase>> = MutableLiveData()
    val action: SingleLiveEvent<Action> = SingleLiveEvent()

    sealed class Action() {
        object ErrorLoadBanner : Action()
        object ShowLatestLoading : Action()
        object ErrorLoadLatest : Action()
        object ShowPopularLoading : Action()
        object ErrorLoadPopular : Action()
        object ShowTopRatedLoading : Action()
        object ErrorLoadTopRated : Action()
        object RedirectToSearch : Action()
        class ShowMovieDetails(val movie: Movie) : Action()
    }
}