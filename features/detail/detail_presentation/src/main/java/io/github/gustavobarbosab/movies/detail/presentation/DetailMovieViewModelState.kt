package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.movies.detail.model.DetailModel

class DetailMovieViewModelState {

    val movie = MutableLiveData<DetailModel>()
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