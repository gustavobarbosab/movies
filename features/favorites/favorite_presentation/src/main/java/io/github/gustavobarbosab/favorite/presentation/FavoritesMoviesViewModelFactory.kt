package io.github.gustavobarbosab.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import javax.inject.Inject

class FavoritesMoviesViewModelFactory @Inject constructor(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FavoritesMoviesViewModel(favoriteMovieUseCase) as T
}