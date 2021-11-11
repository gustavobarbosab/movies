package io.github.gustavobarbosab.core.di.dependencies

import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.database.movie.MovieDao
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import retrofit2.Retrofit

interface CoreDependencies {
    fun provideMovieDao(): MovieDao
    fun provideSessionRepository(): SessionRepository
    fun provideRetrofit(): Retrofit
    fun provideAppConfiguration(): AppConfigWrapper
    fun provideMovieApi(): MovieApi
}