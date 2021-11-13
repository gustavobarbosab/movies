package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.core.network.coroutine.CoroutineResultHandler
import io.github.gustavobarbosab.detail.model.MovieState
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
                this@MovieDetailViewModel::searchFavoriteMovieSuccess,
                this@MovieDetailViewModel::searchFavoritesFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun searchFavoriteMovieSuccess(movieState: MovieState?) {
        state.favoriteButtonState.value = when (movieState) {
            MovieState.MovieLiked -> ButtonState.Filled
            else -> ButtonState.Outline
        }
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

    private fun favoriteMovieSuccess(movieState: MovieState?) {
        when (movieState) {
            MovieState.MovieLiked -> updateButtonState(ButtonState.Filled, ViewAction.MovieLiked)
            else -> updateButtonState(ButtonState.Outline, ViewAction.MovieUnliked)
        }
    }

    private fun updateButtonState(newState: ButtonState, viewAction: ViewAction) {
        state.favoriteButtonState.value = newState
        state.actions.value = viewAction
    }

    private fun favoriteMovieFailure() {
        state.actions.value = ViewAction.FavoriteMovieFailure
    }
}