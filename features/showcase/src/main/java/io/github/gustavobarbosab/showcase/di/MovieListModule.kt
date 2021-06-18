package io.github.gustavobarbosab.showcase.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewModel
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.domain.ShowCaseUseCaseImpl
import retrofit2.Retrofit

@Module
class MovieListModule {

    @Provides
    @ModuleScope
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @ModuleScope
    fun provideViewModel(movieUseCase: ShowCaseUseCase) = ShowCaseViewModel(movieUseCase)

    @Provides
    @ModuleScope
    fun provideUseCase(
        repository: MovieRepository,
        sessionRepository: SessionRepository
    ): ShowCaseUseCase = ShowCaseUseCaseImpl(repository, sessionRepository)
}