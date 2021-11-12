package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.gustavobarbosab.coroutinesresult.CoroutineResult

interface FavoriteMovieUseCase {
    suspend fun favoriteMovie(movie: MovieDetailDomain): CoroutineResult<Unit>
    suspend fun isMovieFavorite(id: Long): CoroutineResult<Boolean>
}