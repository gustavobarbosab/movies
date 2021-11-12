package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.model.MovieDetail
import io.gustavobarbosab.coroutinesresult.CoroutineResult

interface MovieDetailUseCase {
    fun favoriteMovie(movieSelected: MovieDetail): CoroutineResult<Nothing>
    fun isMovieFavorite(movieSelected: MovieDetail): CoroutineResult<Boolean>
}