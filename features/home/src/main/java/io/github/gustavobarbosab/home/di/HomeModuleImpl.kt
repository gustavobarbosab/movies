package io.github.gustavobarbosab.home.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.router.HomeModule
import io.github.gustavobarbosab.core.router.HomeRouter
import io.github.gustavobarbosab.home.HomeRouterImpl

@Module
class HomeModuleImpl : HomeModule {

    @Provides
    fun provideHomeRouter(): HomeRouter = HomeRouterImpl()
}