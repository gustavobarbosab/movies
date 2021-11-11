package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.SimpleResponse

interface ShowCaseUseCase {
    suspend fun getPopularMovies(): SimpleResponse<List<MovieShowCase>>
    suspend fun getTopRatedMovies(): SimpleResponse<List<MovieShowCase>>
    suspend fun getPlayingNow(): SimpleResponse<List<MovieShowCase>>
    suspend fun getLatestMovies(): SimpleResponse<List<MovieShowCase>>
}