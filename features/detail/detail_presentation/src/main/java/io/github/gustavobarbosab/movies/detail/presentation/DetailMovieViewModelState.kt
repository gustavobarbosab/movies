package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.movies.detail.model.DetailModel

class DetailMovieViewModelState {

    val movie = MutableLiveData<DetailModel>()
    val favoriteButtonState = MutableLiveData<ButtonState>()
    val actions: MutableLiveData<ViewAction> = SingleLiveEvent()

    sealed class ViewAction() {
        object ShowLoading : ViewAction()
        object HideLoading : ViewAction()
        object MovieLiked : ViewAction()
        object MovieUnliked : ViewAction()
        object FavoriteMovieFailure : ViewAction()
        object StartScreenFailure : ViewAction()
    }

    sealed class ButtonState {
        object Filled: ButtonState()
        object Outline: ButtonState()
    }
}