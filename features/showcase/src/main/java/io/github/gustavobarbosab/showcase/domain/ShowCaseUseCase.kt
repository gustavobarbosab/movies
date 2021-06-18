package io.github.gustavobarbosab.showcase.domain

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase

interface ShowCaseUseCase {
    suspend fun getPopularMovies(): Result<List<MovieShowCase>>
}