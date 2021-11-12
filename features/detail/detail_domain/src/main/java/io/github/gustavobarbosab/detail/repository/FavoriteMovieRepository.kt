package io.github.gustavobarbosab.detail.repository

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.gustavobarbosab.coroutinesresult.CoroutineResult

interface FavoriteMovieRepository {
    suspend fun isMovieFavorite(id: Long): CoroutineResult<Boolean>
    suspend fun likeMovie(movie: MovieDetailDomain): CoroutineResult<Unit>
    suspend fun dislikeMovie(movie: MovieDetailDomain): CoroutineResult<Unit>
}