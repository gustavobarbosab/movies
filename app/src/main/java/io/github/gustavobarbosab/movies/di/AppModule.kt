package io.github.gustavobarbosab.movies.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.di.dependencies.ApplicationDependencies
import javax.inject.Singleton

@Module
class AppModule : ApplicationDependencies {

    @Provides
    @Singleton
    override fun provideString(): String = "Gustavo Barbosa"
}