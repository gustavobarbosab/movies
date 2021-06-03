package io.github.gustavobarbosab.core.domain.usecase

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie

interface MovieUseCase {
    suspend fun getPopularMovies(): Result<List<Movie>>
}