package io.github.gustavobarbosab.favorite.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.favorite.model.FavoriteModel
import io.github.gustavobarbosab.favorite.model.FavoritesPresentationMapper
import io.github.gustavobarbosab.favorite.presentation.FavoritesMoviesState.*
import io.github.gustavobarbosab.favorite.presentation.FavoritesMoviesState.LayoutState.*
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import io.gustavobarbosab.suspendresult.CoroutineResultHandler
import javax.inject.Inject

class FavoritesMoviesViewModel @Inject constructor(
    private val useCase: FavoriteMovieUseCase
) : BaseViewModel<FavoritesMoviesState>(), CoroutineResultHandler {

    private val mapper = FavoritesPresentationMapper()

    override val state: FavoritesMoviesState = FavoritesMoviesState()

    fun fetchFavorites() {
        viewModelScope.launchMain {
            state.layout.value = HideAll
            state.actions.value = ViewAction.ShowLoading
            handleResult(
                useCase.fetchFavorites(),
                this@FavoritesMoviesViewModel::fetchFavoritesSuccess,
                this@FavoritesMoviesViewModel::fetchFavoritesFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun fetchFavoritesSuccess(result: List<MovieFavorite>?) {
        val favorites = result ?: emptyList()
        val modelList = favorites.map(mapper::map)
        handleFavoritesList(modelList)
    }

    private fun handleFavoritesList(favorites: List<FavoriteModel>?) {
        if (favorites.isNullOrEmpty()) {
            state.layout.value = ShowEmptyState
            return
        }
        state.movies.value = favorites.toMutableList()
        state.layout.value = ShowRecyclerView
    }

    private fun fetchFavoritesFailure() {
        state.layout.value = ShowTryAgain
    }

    fun unlikeMovie(model: FavoriteModel, position: Int) {
        viewModelScope.launchMain {
            state.actions.value = ViewAction.ShowLoading
            handleResult(
                useCase.unlikeMovie(model.id),
                { unlikeMovieSuccess(position) },
                this@FavoritesMoviesViewModel::unlikeMovieFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun unlikeMovieSuccess(position: Int) {
        state.actions.value = ViewAction.MovieUnliked
        state.actions.value = ViewAction.RemoveUnlikedMovie(position)

        val favoritesMovies = state.movies.value
        if (favoritesMovies.isNullOrEmpty()) {
            state.layout.value = ShowEmptyState
        }
    }

    private fun unlikeMovieFailure() {
        state.actions.value = ViewAction.UnlikeMovieFailure
    }
}