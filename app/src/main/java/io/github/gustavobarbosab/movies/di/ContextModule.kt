package io.github.gustavobarbosab.movies.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import io.github.gustavobarbosab.core.di.dependencies.ContextDependenciesContract
import javax.inject.Singleton

@Module
interface ContextModule : ContextDependenciesContract {
    @Binds
    @Singleton
    fun provideContext(application: Application): Context
}