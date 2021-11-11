package io.github.gustavobarbosab.movies.data.datasources.remote

import io.github.gustavobarbosab.core.network.services.movies.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowCaseRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun getPopularMovies() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getPopularMovies()
    }

    suspend fun getTopRatedMovies() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getTopRatedMovies()
    }

    suspend fun getPlayingNow() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getPlayingNow()
    }

    suspend fun getLatestMovies() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getLatestMovies()
    }
}