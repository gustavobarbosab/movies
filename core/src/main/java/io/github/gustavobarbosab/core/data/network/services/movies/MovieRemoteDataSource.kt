package io.github.gustavobarbosab.core.data.network.services.movies

import io.github.gustavobarbosab.core.result.SafelyCall

class MovieRemoteDataSource(private val movieApi: MovieApi) : SafelyCall {
    suspend fun getPopularMovies() = safeCallOnIo(movieApi::getPopularMovies)

    suspend fun getTopRatedMovies() = safeCallOnIo(movieApi::getTopRatedMovies)

    suspend fun getPlayingNow() = safeCallOnIo(movieApi::getPlayingNow)

    suspend fun getLatestMovies() = safeCallOnIo(movieApi::getLatestMovies)
}