package io.github.gustavobarbosab.movies.detail.data.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.detail.repository.FavoriteMovieRepository
import io.github.gustavobarbosab.detail.usecase.FavoriteMovieUseCase
import io.github.gustavobarbosab.detail.usecase.FavoriteMovieUseCaseImpl
import io.github.gustavobarbosab.movies.detail.data.favorite.FavoriteMovieRepositoryImpl

@Module
class DetailDataModule {

    @Provides
    fun provideUseCase(favoriteMovieRepository: FavoriteMovieRepositoryImpl): FavoriteMovieUseCase =
        FavoriteMovieUseCaseImpl(favoriteMovieRepository)
}