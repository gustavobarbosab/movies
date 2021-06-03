package io.github.gustavobarbosab.core.di.modules

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.data.database.movie.MovieDao
import io.github.gustavobarbosab.core.data.database.movie.MovieLocalDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.github.gustavobarbosab.core.data.network.services.movies.MovieRemoteDataSource
import io.github.gustavobarbosab.core.data.repositories.movies.MovieRepositoryImpl
import io.github.gustavobarbosab.core.data.repositories.session.SessionRepositoryImpl
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryModule(
        localDataSource: MovieLocalDataSource,
        remoteDataSource: MovieRemoteDataSource
    ): MovieRepository = MovieRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    @Singleton
    fun provideLocalMovieDataSource(
        movieDao: MovieDao
    ): MovieLocalDataSource = MovieLocalDataSource(movieDao)

    @Provides
    @Singleton
    fun provideRemoteMovieDataSource(
        api: MovieApi
    ): MovieRemoteDataSource = MovieRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieDao(): MovieDao = MovieDao()

    @Provides
    @Singleton
    fun provideRepository(): SessionRepository = SessionRepositoryImpl()
}