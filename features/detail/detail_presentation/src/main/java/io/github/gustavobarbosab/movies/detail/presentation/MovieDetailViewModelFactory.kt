package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.detail.usecase.MovieDetailUseCase

class MovieDetailViewModelFactory(
    private val movieDetailUseCase: MovieDetailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailViewModel(movieDetailUseCase) as T
}