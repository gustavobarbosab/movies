package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.gustavobarbosab.coroutinesresult.CoroutineResult

class DetailUseCaseImpl : DetailUseCase {

    override fun favoriteMovie(movieSelected: MovieDetailDomain): CoroutineResult<Unit> {
        return CoroutineResult.Success(Unit)
    }

    override fun isMovieFavorite(movieId: Long): CoroutineResult<Boolean> {
        return CoroutineResult.Success(false)
    }
}