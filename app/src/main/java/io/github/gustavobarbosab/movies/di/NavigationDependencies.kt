package io.github.gustavobarbosab.movies.di

import io.github.gustavobarbosab.detail.MovieDetail
import io.github.gustavobarbosab.movies.navigation.MovieBaseNavigation

interface NavigationDependencies {
    fun provideDetailsNavigation(): MovieBaseNavigation<MovieDetail>
}