package io.github.gustavobarbosab.core.data.repositories.session

import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult
import java.lang.Exception

internal class SessionRepositoryImpl : SessionRepository {
    override suspend fun favoriteMovies(): CoroutineResult<List<Movie>> {
        return CoroutineResult.Error(Exception())
    }
}