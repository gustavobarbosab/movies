package io.github.gustavobarbosab.core.domain.repository

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie

interface SessionRepository {
    suspend fun favoriteMovies(): Result<List<Movie>>
}