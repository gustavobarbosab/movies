package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.detail.model.MovieUpdate
import io.gustavobarbosab.coroutinesresult.CoroutineResult

interface FavoriteMovieUseCase {
    suspend fun updateFavoriteMovie(movie: MovieDetailDomain): CoroutineResult<MovieUpdate>
    suspend fun isMovieFavorite(id: Long): CoroutineResult<Boolean>
}