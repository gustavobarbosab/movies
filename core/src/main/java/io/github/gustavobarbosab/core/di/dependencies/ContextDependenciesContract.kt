package io.github.gustavobarbosab.core.di.dependencies

import android.app.Application
import android.content.Context

interface ContextDependenciesContract {
    fun provideContext(): Context
    fun provideApplication(): Application
}