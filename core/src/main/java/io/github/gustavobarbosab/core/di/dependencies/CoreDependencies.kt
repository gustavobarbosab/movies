package io.github.gustavobarbosab.core.di.dependencies

import io.github.gustavobarbosab.core.config.AppConfigWrapper
import io.github.gustavobarbosab.core.database.MovieDao
import io.github.gustavobarbosab.core.network.services.movies.MovieApi
import retrofit2.Retrofit

interface CoreDependencies {
    fun provideMovieDao(): MovieDao
    fun provideRetrofit(): Retrofit
    fun provideAppConfiguration(): AppConfigWrapper
    fun provideMovieApi(): MovieApi
}