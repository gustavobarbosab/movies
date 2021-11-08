package io.github.gustavobarbosab.showcase.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.database.movie.MovieDao
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.showcase.data.ShowCaseRepositoryImpl
import io.github.gustavobarbosab.showcase.data.ShowCaseRepositoryMapper
import io.github.gustavobarbosab.showcase.data.datasources.local.ShowCaseLocalDataSource
import io.github.gustavobarbosab.showcase.data.datasources.remote.ShowCaseRemoteDataSource
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewModelFactory
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCaseImpl

@Module
class MovieListModule {

    @Provides
    @ModuleScope
    fun provideViewModelFactory(movieUseCase: ShowCaseUseCase) =
        ShowCaseViewModelFactory(movieUseCase)

    @Provides
    @ModuleScope
    fun provideUseCase(
        repository: ShowCaseRepository,
        sessionRepository: SessionRepository
    ): ShowCaseUseCase = ShowCaseUseCaseImpl(repository, sessionRepository)


    @Provides
    @ModuleScope
    fun provideMovieRepositoryModule(
        localDataSource: ShowCaseLocalDataSource,
        remoteDataSource: ShowCaseRemoteDataSource,
        mapper: ShowCaseRepositoryMapper
    ): ShowCaseRepository = ShowCaseRepositoryImpl(
        localDataSource,
        remoteDataSource,
        mapper
    )

    @Provides
    @ModuleScope
    fun provideLocalMovieDataSource(movieDao: MovieDao): ShowCaseLocalDataSource =
        ShowCaseLocalDataSource(movieDao)

    @Provides
    @ModuleScope
    fun provideRemoteMovieDataSource(api: MovieApi): ShowCaseRemoteDataSource =
        ShowCaseRemoteDataSource(api)

    @Provides
    @ModuleScope
    fun provideMapper(configWrapper: AppConfigWrapper) = ShowCaseRepositoryMapper(configWrapper)
}