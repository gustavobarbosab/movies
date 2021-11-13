package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.detail.model.MovieState
import io.gustavobarbosab.coroutinesresult.SuspendResult

interface FavoriteMovieUseCase {
    suspend fun updateFavoriteMovie(movie: MovieDetailDomain): SuspendResult<MovieState>
    suspend fun isMovieFavorite(id: Long): SuspendResult<MovieState>
}