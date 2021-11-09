package io.github.gustavobarbosab.movies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.github.gustavobarbosab.core.di.dependencies.CoreDependencies
import io.github.gustavobarbosab.core.di.dependencies.NetworkDependencies
import io.github.gustavobarbosab.core.di.modules.CoreModule
import io.github.gustavobarbosab.core.di.modules.NetworkModule
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies.MovieApplication
import io.github.gustavobarbosab.movies.data.di.ShowCaseDataModule
import io.github.gustavobarbosab.movies.data.di.ShowCaseDependencies
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        CoreModule::class,
        NetworkModule::class,
        ShowCaseDataModule::class
    ]
)
interface AppComponent :
    ContextDependencies,
    CoreDependencies,
    NetworkDependencies {

    fun provideShowCaseUseCase(): ShowCaseUseCase

    fun inject(application: MovieApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
