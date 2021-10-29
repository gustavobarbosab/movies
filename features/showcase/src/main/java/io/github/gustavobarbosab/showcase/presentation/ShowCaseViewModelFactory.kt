package io.github.gustavobarbosab.showcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCase
import javax.inject.Inject

class ShowCaseViewModelFactory @Inject constructor(val useCase: ShowCaseUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowCaseViewModel(useCase) as T
}