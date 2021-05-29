package io.github.gustavobarbosab.core.di.dependencies

import retrofit2.Retrofit

interface NetworkDependenciesContract {
    fun provideRetrofit(): Retrofit
}