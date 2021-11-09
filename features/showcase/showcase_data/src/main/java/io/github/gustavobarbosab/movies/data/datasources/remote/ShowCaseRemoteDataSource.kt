package io.github.gustavobarbosab.movies.data.datasources.remote

import io.github.gustavobarbosab.core.data.network.services.movies.MovieApi
import io.gustavobarbosab.coroutinesresult.SafelyCoroutineCall
import javax.inject.Inject

class ShowCaseRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) : SafelyCoroutineCall {

    suspend fun getPopularMovies() = safeCallOnIo(movieApi::getPopularMovies)

    suspend fun getTopRatedMovies() = safeCallOnIo(movieApi::getTopRatedMovies)

    suspend fun getPlayingNow() = safeCallOnIo(movieApi::getPlayingNow)

    suspend fun getLatestMovies() = safeCallOnIo(movieApi::getLatestMovies)
}