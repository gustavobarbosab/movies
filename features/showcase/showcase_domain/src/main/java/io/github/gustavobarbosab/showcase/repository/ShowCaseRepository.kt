package io.github.gustavobarbosab.showcase.repository

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.SuspendResult

interface ShowCaseRepository {
    suspend fun getPopularMovies(): SuspendResult<List<MovieShowCase>>

    suspend fun getTopRatedMovies(): SuspendResult<List<MovieShowCase>>

    suspend fun getPlayingNow(): SuspendResult<List<MovieShowCase>>

    suspend fun getLatestMovies(): SuspendResult<List<MovieShowCase>>
}