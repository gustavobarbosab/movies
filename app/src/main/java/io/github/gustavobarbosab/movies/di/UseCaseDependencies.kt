package io.github.gustavobarbosab.movies.di

import io.github.gustavobarbosab.movies.navigation.detail.DetailModelMapper
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase

interface UseCaseDependencies {
    fun provideShowCaseUseCase(): ShowCaseUseCase
}