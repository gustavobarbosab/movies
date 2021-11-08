package io.github.gustavobarbosab.showcase.repository

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult

interface ShowCaseRepository {
    suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>>

    suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>>

    suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>>

    suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>>
}