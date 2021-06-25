package io.github.gustavobarbosab.core.di.dependencies

import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository

interface RepositoryDependenciesContract {
    fun provideMovieRepository(): MovieRepository
    fun provideSessionRepository(): SessionRepository
}