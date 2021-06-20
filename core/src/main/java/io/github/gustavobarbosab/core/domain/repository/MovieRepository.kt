package io.github.gustavobarbosab.core.domain.repository

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): Result<List<Movie>>

    suspend fun getTopRatedMovies(): Result<List<Movie>>

    suspend fun getPlayingNow(): Result<List<Movie>>

    suspend fun getLatestMovies(): Result<List<Movie>>
}