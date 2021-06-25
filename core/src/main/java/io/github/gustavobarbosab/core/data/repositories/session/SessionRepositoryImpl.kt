package io.github.gustavobarbosab.core.data.repositories.session

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import java.lang.Exception

internal class SessionRepositoryImpl : SessionRepository {
    override suspend fun favoriteMovies(): Result<List<Movie>> {
        return Result.Error(Exception())
    }
}