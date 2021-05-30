package io.github.gustavobarbosab.movies_list.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies_list.MovieListViewModel
import io.github.gustavobarbosab.movies_list.data.MovieApi
import retrofit2.Retrofit

@Module
class MovieListModule {

    @Provides
    @ModuleScope
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @ModuleScope
    fun provideViewModel(movieApi: MovieApi) = MovieListViewModel(movieApi)
}