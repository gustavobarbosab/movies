package io.github.gustavobarbosab.showcase.domain

import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult

interface ShowCaseUseCase {
    suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>>
    suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>>
    suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>>
    suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>>
    fun movie(showCase: MovieShowCase): Movie
}