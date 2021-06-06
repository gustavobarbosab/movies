package io.github.gustavobarbosab.core.di.dependencies

import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import retrofit2.Retrofit

interface NetworkDependenciesContract {
    fun provideRetrofit(): Retrofit
    fun provideAppConfiguration(): AppConfigWrapper
}