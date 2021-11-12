package io.github.gustavobarbosab.movies.di

import io.github.gustavobarbosab.detail.usecase.DetailUseCase
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase

interface UseCaseDependencies {
    fun provideShowCaseUseCase(): ShowCaseUseCase
    fun provideDetailUseCase(): DetailUseCase
}