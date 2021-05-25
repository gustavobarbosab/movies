package io.github.gustavobarbosab.home.di

import dagger.Component
import io.github.gustavobarbosab.home.HomeFragment
import io.github.gustavobarbosab.movies.di.AppComponent

@Component(modules = [HomeModuleImpl::class])
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}