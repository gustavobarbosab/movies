package io.github.gustavobarbosab.movies_list.di

import dagger.Component
import io.github.gustavobarbosab.core.di.ModuleScope
import io.github.gustavobarbosab.movies.di.AppComponent
import io.github.gustavobarbosab.movies_list.MovieListFragment

@ModuleScope
@Component(
    modules = [MovieListModule::class],
    dependencies = [AppComponent::class]
)
interface MovieListComponent {

    fun inject(fragment: MovieListFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppComponent): MovieListComponent
    }
}