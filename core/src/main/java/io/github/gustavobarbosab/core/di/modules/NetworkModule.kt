package io.github.gustavobarbosab.core.di.modules

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.di.dependencies.NetworkDependenciesContract
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule: NetworkDependenciesContract {

    @Provides
    @Singleton
    override fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}