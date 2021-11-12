package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.gustavobarbosab.coroutinesresult.CoroutineResult

interface MovieDetailUseCase {
    fun favoriteMovie(movieSelected: MovieDetailDomain): CoroutineResult<Nothing>
    fun isMovieFavorite(movieId: Long): CoroutineResult<Boolean>
}