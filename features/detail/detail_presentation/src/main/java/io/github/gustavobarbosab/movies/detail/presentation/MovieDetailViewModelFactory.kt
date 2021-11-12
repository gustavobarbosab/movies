package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.detail.usecase.DetailUseCase
import javax.inject.Inject

class MovieDetailViewModelFactory @Inject constructor(
    private val movieDetailUseCase: DetailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailViewModel(movieDetailUseCase) as T
}