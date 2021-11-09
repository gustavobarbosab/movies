package io.github.gustavobarbosab.movies.data.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.database.movie.MovieDao
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies.data.ShowCaseRepositoryImpl
import io.github.gustavobarbosab.movies.data.ShowCaseRepositoryMapper
import io.github.gustavobarbosab.movies.data.datasources.local.ShowCaseLocalDataSource
import io.github.gustavobarbosab.movies.data.datasources.remote.ShowCaseRemoteDataSource
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCaseImpl
import javax.inject.Singleton

@Module
class ShowCaseDataModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: ShowCaseRepository): ShowCaseUseCase =
        ShowCaseUseCaseImpl(repository)

    @Provides
    fun provideMovieRepository(
        localDataSource: ShowCaseLocalDataSource,
        remoteDataSource: ShowCaseRemoteDataSource,
        mapper: ShowCaseRepositoryMapper
    ): ShowCaseRepository = ShowCaseRepositoryImpl(
        localDataSource,
        remoteDataSource,
        mapper
    )

    @Provides
    fun provideLocalMovieDataSource(movieDao: MovieDao): ShowCaseLocalDataSource =
        ShowCaseLocalDataSource(movieDao)

    @Provides
    fun provideRemoteMovieDataSource(api: MovieApi): ShowCaseRemoteDataSource =
        ShowCaseRemoteDataSource(api)

    @Provides
    fun provideMapper(configWrapper: AppConfigWrapper) =
        ShowCaseRepositoryMapper(configWrapper)
}