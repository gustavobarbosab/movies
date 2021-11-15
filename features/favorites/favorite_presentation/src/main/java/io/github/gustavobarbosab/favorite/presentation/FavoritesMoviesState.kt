package io.github.gustavobarbosab.favorite.presentation

import android.text.Layout
import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.favorite.model.FavoriteModel

class FavoritesMoviesState {
    val movies = MutableLiveData<MutableList<FavoriteModel>>()
    val layout = MutableLiveData<LayoutState>()
    val actions = SingleLiveEvent<ViewAction>()

    sealed class ViewAction {
        object ShowLoading : ViewAction()
        object HideLoading : ViewAction()
        object MovieUnliked : ViewAction()
        object UnlikeMovieFailure : ViewAction()
        class RemoveUnlikedMovie(val position: Int) : ViewAction()
    }

    sealed class LayoutState {
        object ShowTryAgain : LayoutState()
        object ShowRecyclerView : LayoutState()
        object ShowEmptyState : LayoutState()
        object HideAll : LayoutState()
    }
}