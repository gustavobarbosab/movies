package io.github.gustavobarbosab.core.data.network.services.movies

import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMoviePagingResponse

class MovieRemoteDataSource(private val movieApi: MovieApi) {
    suspend fun getPopularMovies(): PopularMoviePagingResponse = movieApi.getPopularMovies()

    suspend fun getTopRatedMovies(): PopularMoviePagingResponse = movieApi.getTopRatedMovies()

    suspend fun getPlayingNow(): PopularMoviePagingResponse = movieApi.getPlayingNow()

    suspend fun getLatestMovies(): PopularMoviePagingResponse = movieApi.getLatestMovies()
}