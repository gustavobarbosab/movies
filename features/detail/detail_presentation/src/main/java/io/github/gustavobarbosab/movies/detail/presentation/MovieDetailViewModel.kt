package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.core.network.coroutine.CoroutineResultHandler
import io.github.gustavobarbosab.detail.model.MovieDetail
import io.github.gustavobarbosab.detail.usecase.MovieDetailUseCase
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ButtonState
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ViewActions

class MovieDetailViewModel(
    private val movieDetailUseCase: MovieDetailUseCase
) : BaseViewModel<DetailMovieViewModelState>(), CoroutineResultHandler {

    override val state: DetailMovieViewModelState = DetailMovieViewModelState()

    private lateinit var movieSelected: MovieDetail

    fun init(movie: MovieDetail) {
        movieSelected = movie
        state.movie.value = movieSelected
        handleFavoriteState()
    }

    private fun handleFavoriteState() {
        viewModelScope.launchMain {
            state.actions.value = ViewActions.ShowLoading
            handleResult(
                movieDetailUseCase.isMovieFavorite(movieSelected),
                this@MovieDetailViewModel::searchFavoritesSuccess,
                this@MovieDetailViewModel::searchFavoritesFailure
            )
            state.actions.value = ViewActions.HideLoading
        }
    }

    private fun searchFavoritesSuccess(result: Boolean?) {
        state.favoriteButtonState.value = if (result == true) ButtonState.Filled else ButtonState.Outline
    }

    private fun searchFavoritesFailure() {
        state.actions.value = ViewActions.SearchFavoriteMoviesFailure
    }

    fun favoriteMovie() {
        viewModelScope.launchMain {
            state.actions.value = ViewActions.ShowLoading
            handleResult(
                movieDetailUseCase.favoriteMovie(movieSelected),
                this@MovieDetailViewModel::favoriteMovieSuccess,
                this@MovieDetailViewModel::favoriteMovieFailure
            )
            state.actions.value = ViewActions.HideLoading
        }
    }

    private fun favoriteMovieSuccess(nothing: Nothing?) {
        state.actions.value = ViewActions.FavoriteMovieSuccess
    }

    private fun favoriteMovieFailure() {
        state.actions.value = ViewActions.FavoriteMovieFailure
    }
}