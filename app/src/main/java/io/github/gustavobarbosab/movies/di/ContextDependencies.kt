package io.github.gustavobarbosab.movies.di

import android.app.Application
import android.content.Context

interface ContextDependencies {
    fun provideApplication(): Application
    fun provideContext(): Context
}