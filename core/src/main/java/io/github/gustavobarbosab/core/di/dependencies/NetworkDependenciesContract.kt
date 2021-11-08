package io.github.gustavobarbosab.core.di.dependencies

import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import retrofit2.Retrofit

interface NetworkDependenciesContract {
    fun provideRetrofit(): Retrofit
    fun provideAppConfiguration(): AppConfigWrapper
    fun provideMovieApi(): MovieApi
}