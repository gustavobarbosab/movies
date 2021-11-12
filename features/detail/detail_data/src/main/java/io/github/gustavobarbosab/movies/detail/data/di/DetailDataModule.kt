package io.github.gustavobarbosab.movies.detail.data.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.detail.usecase.DetailUseCase
import io.github.gustavobarbosab.detail.usecase.DetailUseCaseImpl

@Module
class DetailDataModule {

    @Provides
    fun provideUseCase(): DetailUseCase = DetailUseCaseImpl()
}