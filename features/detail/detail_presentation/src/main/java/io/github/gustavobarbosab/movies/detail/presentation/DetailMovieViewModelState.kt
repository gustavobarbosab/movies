package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.detail.model.MovieDetail

class DetailMovieViewModelState {

    val movie = MutableLiveData<MovieDetail>()
    val favoriteButtonState = MutableLiveData<ButtonState>()
    val actions: MutableLiveData<ViewActions> = SingleLiveEvent()

    sealed class ViewActions() {
        object ShowLoading : ViewActions()
        object HideLoading : ViewActions()
        object FavoriteMovieSuccess : ViewActions()
        object FavoriteMovieFailure : ViewActions()
        object SearchFavoriteMoviesFailure : ViewActions()
    }

    sealed class ButtonState {
        object Filled: ButtonState()
        object Outline: ButtonState()
    }
}