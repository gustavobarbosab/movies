package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.core.network.coroutine.CoroutineResultHandler
import io.github.gustavobarbosab.detail.model.MovieUpdate
import io.github.gustavobarbosab.detail.usecase.FavoriteMovieUseCase
import io.github.gustavobarbosab.movies.detail.model.DetailModel
import io.github.gustavobarbosab.movies.detail.model.DetailPresentationMapper
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ButtonState
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ViewAction
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailArgument
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteMovieUseCase
) : BaseViewModel<DetailMovieViewModelState>(), CoroutineResultHandler {

    override val state: DetailMovieViewModelState = DetailMovieViewModelState()
    private val mapper = DetailPresentationMapper()

    private lateinit var movieSelected: DetailModel

    fun init(movie: MovieDetailArgument) {
        if (isScreenAlreadyCreated())
            return

        movieSelected = mapper.map(movie)
        state.movie.value = movieSelected
        handleFavoriteState()
    }

    private fun isScreenAlreadyCreated() = this::movieSelected.isInitialized

    private fun handleFavoriteState() {
        viewModelScope.launchMain {
            state.actions.value = ViewAction.ShowLoading
            handleResult(
                favoriteUseCase.isMovieFavorite(movieSelected.id),
                this@MovieDetailViewModel::searchFavoritesSuccess,
                this@MovieDetailViewModel::searchFavoritesFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun searchFavoritesSuccess(result: Boolean?) {
        val newState = if (result == true) ButtonState.Filled else ButtonState.Outline
        state.favoriteButtonState.value = newState
    }

    private fun searchFavoritesFailure() {
        state.actions.value = ViewAction.StartScreenFailure
    }

    fun favoriteMovie() {
        viewModelScope.launchMain {
            state.actions.value = ViewAction.ShowLoading
            val detailDomain = mapper.map(movieSelected)
            handleResult(
                favoriteUseCase.updateFavoriteMovie(detailDomain),
                this@MovieDetailViewModel::favoriteMovieSuccess,
                this@MovieDetailViewModel::favoriteMovieFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun favoriteMovieSuccess(update: MovieUpdate?) {
        val pairResult = when (update) {
            MovieUpdate.MovieLiked -> Pair(ButtonState.Filled, ViewAction.MovieLiked)
            else -> Pair(ButtonState.Outline, ViewAction.MovieUnliked)
        }
        state.favoriteButtonState.value = pairResult.first
        state.actions.value = pairResult.second
    }

    private fun favoriteMovieFailure() {
        state.actions.value = ViewAction.FavoriteMovieFailure
    }
}