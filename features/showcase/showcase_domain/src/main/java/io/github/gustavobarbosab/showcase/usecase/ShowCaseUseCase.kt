package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.CoroutineResult

interface ShowCaseUseCase {
    suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>>
    suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>>
    suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>>
    suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>>
}