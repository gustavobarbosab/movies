package io.github.gustavobarbosab.detail.repository

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.gustavobarbosab.suspendresult.SuspendResult

interface FavoriteMovieRepository {
    suspend fun isMovieFavorite(id: Long): SuspendResult<Boolean>
    suspend fun likeMovie(movie: MovieDetailDomain): SuspendResult<Unit>
    suspend fun dislikeMovie(movie: MovieDetailDomain): SuspendResult<Unit>
}