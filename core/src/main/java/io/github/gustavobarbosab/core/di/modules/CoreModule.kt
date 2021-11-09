package io.github.gustavobarbosab.core.di.modules

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.database.movie.MovieDao
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.github.gustavobarbosab.core.data.repositories.session.SessionRepositoryImpl
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
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

    @Provides
    @Singleton
    fun provideRepository(): SessionRepository = SessionRepositoryImpl()
}