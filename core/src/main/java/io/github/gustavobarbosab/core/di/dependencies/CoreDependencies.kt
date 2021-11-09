package io.github.gustavobarbosab.core.di.dependencies

import io.github.gustavobarbosab.core.data.database.movie.MovieDao
import io.github.gustavobarbosab.core.domain.repository.SessionRepository

interface CoreDependencies {
    fun provideMovieDao(): MovieDao
    fun provideSessionRepository(): SessionRepository
}