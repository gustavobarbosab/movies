package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.core.network.coroutine.CoroutineResultHandler
import io.github.gustavobarbosab.detail.usecase.MovieDetailUseCase
import io.github.gustavobarbosab.movies.detail.model.DetailModel
import io.github.gustavobarbosab.movies.detail.model.DetailPresentationMapper
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ButtonState
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ViewActions
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailArgument
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase
) : BaseViewModel<DetailMovieViewModelState>(), CoroutineResultHandler {

    override val state: DetailMovieViewModelState = DetailMovieViewModelState()
    private val mapper = DetailPresentationMapper()

    private lateinit var movieSelected: DetailModel

    fun init(movie: MovieDetailArgument) {
        movieSelected = mapper.map(movie)
        state.movie.value = movieSelected
        handleFavoriteState()
    }

    private fun handleFavoriteState() {
        viewModelScope.launchMain {
            state.actions.value = ViewActions.ShowLoading
            handleResult(
                movieDetailUseCase.isMovieFavorite(movieSelected.id),
                this@MovieDetailViewModel::searchFavoritesSuccess,
                this@MovieDetailViewModel::searchFavoritesFailure
            )
            state.actions.value = ViewActions.HideLoading
        }
    }

    private fun searchFavoritesSuccess(result: Boolean?) {
        val newState = if (result == true) ButtonState.Filled else ButtonState.Outline
        state.favoriteButtonState.value = newState
    }

    private fun searchFavoritesFailure() {
        state.actions.value = ViewActions.SearchFavoriteMoviesFailure
    }

    fun favoriteMovie() {
        viewModelScope.launchMain {
            state.actions.value = ViewActions.ShowLoading
            val detailDomain = mapper.map(movieSelected)
            handleResult(
                movieDetailUseCase.favoriteMovie(detailDomain),
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