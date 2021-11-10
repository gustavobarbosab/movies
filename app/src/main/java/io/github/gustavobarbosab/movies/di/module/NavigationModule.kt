package io.github.gustavobarbosab.movies.di.module

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.detail.MovieDetail
import io.github.gustavobarbosab.movies.navigation.MovieBaseNavigation
import io.github.gustavobarbosab.movies.navigation.detail.DetailsNavigation
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideDetailsNavigation(): MovieBaseNavigation<MovieDetail> = DetailsNavigation()
}