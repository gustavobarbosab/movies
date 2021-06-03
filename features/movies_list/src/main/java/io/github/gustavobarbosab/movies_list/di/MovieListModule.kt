package io.github.gustavobarbosab.movies_list.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies_list.MovieListViewModel
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.core.domain.usecase.MovieUseCase
import io.github.gustavobarbosab.movies_list.MovieListUseCase
import retrofit2.Retrofit

@Module
class MovieListModule {

    @Provides
    @ModuleScope
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @ModuleScope
    fun provideViewModel(movieUseCase: MovieUseCase) = MovieListViewModel(movieUseCase)

    @Provides
    @ModuleScope
    fun provideUseCase(
        repository: MovieRepository,
        sessionRepository: SessionRepository
    ): MovieUseCase = MovieListUseCase(repository, sessionRepository)
}