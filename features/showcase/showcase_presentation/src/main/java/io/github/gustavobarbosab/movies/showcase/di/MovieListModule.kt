package io.github.gustavobarbosab.showcase.showcase.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.showcase.showcase.presentation.ShowCaseViewModelFactory
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase

@Module
class MovieListModule {

    @Provides
    @FeatureScope
    fun provideViewModelFactory(movieUseCase: ShowCaseUseCase) = ShowCaseViewModelFactory(movieUseCase)
}