package io.github.gustavobarbosab.showcase.repository

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.SimpleResponse

interface ShowCaseRepository {
    suspend fun getPopularMovies(): SimpleResponse<List<MovieShowCase>>

    suspend fun getTopRatedMovies(): SimpleResponse<List<MovieShowCase>>

    suspend fun getPlayingNow(): SimpleResponse<List<MovieShowCase>>

    suspend fun getLatestMovies(): SimpleResponse<List<MovieShowCase>>
}