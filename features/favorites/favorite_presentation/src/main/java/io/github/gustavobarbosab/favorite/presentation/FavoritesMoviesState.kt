package io.github.gustavobarbosab.favorite.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.favorite.model.FavoriteModel

class FavoritesMoviesState {

    val movies = MutableLiveData<List<FavoriteModel>>()
    val actions = SingleLiveEvent<ViewAction>()

    sealed class ViewAction {
        object ShowLoading : ViewAction()
        object HideLoading : ViewAction()
        object LoadFavoritesFailure : ViewAction()
        object MovieUnliked : ViewAction()
        object UnlikeMovieFailure : ViewAction()
        class RemoveUnlikedMovie(val position: Int) : ViewAction()
    }
}