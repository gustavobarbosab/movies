package io.github.gustavobarbosab.movies.di

import dagger.BindsInstance
import dagger.Component
import io.github.gustavobarbosab.core.di.ApplicationDependencies
import io.github.gustavobarbosab.movies.MovieApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent: ApplicationDependencies {

    fun inject(application: MovieApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MovieApplication): Builder

        fun build(): AppComponent
    }
}