package io.github.gustavobarbosab.core.domain.repository

import io.github.gustavobarbosab.core.domain.model.Movie
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult

interface MovieRepository {
    suspend fun getPopularMovies(): CoroutineResult<List<Movie>>

    suspend fun getTopRatedMovies(): CoroutineResult<List<Movie>>

    suspend fun getPlayingNow(): CoroutineResult<List<Movie>>

    suspend fun getLatestMovies(): CoroutineResult<List<Movie>>
}