package io.github.gustavobarbosab.movies.data.datasources.remote

import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.gustavobarbosab.coroutinesresult.SafelyCoroutineCall
import javax.inject.Inject

class ShowCaseRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) : SafelyCoroutineCall() {

    suspend fun getPopularMovies() = safeCallIo(movieApi::getPopularMovies)

    suspend fun getTopRatedMovies() = safeCallIo(movieApi::getTopRatedMovies)

    suspend fun getPlayingNow() = safeCallIo(movieApi::getPlayingNow)

    suspend fun getLatestMovies() = safeCallIo(movieApi::getLatestMovies)
}