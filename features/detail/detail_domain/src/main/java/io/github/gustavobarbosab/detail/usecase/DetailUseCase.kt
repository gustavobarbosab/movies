package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.gustavobarbosab.coroutinesresult.CoroutineResult

interface DetailUseCase {
    fun favoriteMovie(movieSelected: MovieDetailDomain): CoroutineResult<Unit>
    fun isMovieFavorite(movieId: Long): CoroutineResult<Boolean>
}