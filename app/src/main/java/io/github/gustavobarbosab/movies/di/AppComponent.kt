package io.github.gustavobarbosab.movies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.github.gustavobarbosab.core.di.dependencies.CoreDependencies
import io.github.gustavobarbosab.core.di.dependencies.NetworkDependencies
import io.github.gustavobarbosab.core.di.modules.CoreModule
import io.github.gustavobarbosab.core.di.modules.NetworkModule
import io.github.gustavobarbosab.movies.MovieApplication
import io.github.gustavobarbosab.movies.di.module.ContextModule
import io.github.gustavobarbosab.movies.di.module.NavigationModule
import io.github.gustavobarbosab.movies.di.module.UseCaseModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        CoreModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        NavigationModule::class
    ]
)
interface AppComponent :
    ContextDependencies,
    CoreDependencies,
    NetworkDependencies,
    UseCaseDependencies,
    NavigationDependencies {

    fun inject(application: MovieApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
