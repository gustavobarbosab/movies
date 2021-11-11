package io.github.gustavobarbosab.core.di.modules

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.config.AppConfigWrapper
import io.github.gustavobarbosab.core.database.MovieDao
import io.github.gustavobarbosab.core.network.services.movies.MovieApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideAppWrapper() = AppConfigWrapper()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieDao(): MovieDao = MovieDao()
}