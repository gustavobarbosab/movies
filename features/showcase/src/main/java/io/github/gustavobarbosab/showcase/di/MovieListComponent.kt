package io.github.gustavobarbosab.showcase.di

import dagger.Component
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies.di.AppComponent
import io.github.gustavobarbosab.showcase.presentation.ShowCaseFragment

@ModuleScope
@Component(
    modules = [MovieListModule::class],
    dependencies = [AppComponent::class]
)
interface MovieListComponent {

    fun inject(fragment: ShowCaseFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppComponent): MovieListComponent
    }
}