package io.github.gustavobarbosab.movies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.github.gustavobarbosab.core.di.dependencies.AppDependenciesContract
import io.github.gustavobarbosab.core.di.dependencies.ContextDependenciesContract
import io.github.gustavobarbosab.core.di.dependencies.NetworkDependenciesContract
import io.github.gustavobarbosab.core.di.modules.NetworkModule
import io.github.gustavobarbosab.core.di.modules.RepositoryModule
import io.github.gustavobarbosab.movies.MovieApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent :
    ContextDependenciesContract,
    AppDependenciesContract,
    NetworkDependenciesContract {

    fun inject(application: MovieApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
