package io.github.gustavobarbosab.core.domain.repository

import io.github.gustavobarbosab.core.domain.model.Movie

interface SessionRepository {
    suspend fun favoriteMovies(): io.gustavobarbosab.coroutinesresult.model.CoroutineResult<List<Movie>>
}