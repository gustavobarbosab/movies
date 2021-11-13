package io.github.gustavobarbosab.movies.detail.data.favorite

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.detail.repository.FavoriteMovieRepository
import io.gustavobarbosab.coroutinesresult.SuspendResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor() : FavoriteMovieRepository {

    override suspend fun isMovieFavorite(id: Long): SuspendResult<Boolean> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(1000)
                var ret = SuspendResult.Success(false)
                return@runBlocking ret
            }
        }


    override suspend fun likeMovie(movie: MovieDetailDomain): SuspendResult<Unit> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(1000)
                return@runBlocking SuspendResult.Success(Unit)
            }
        }

    override suspend fun dislikeMovie(movie: MovieDetailDomain): SuspendResult<Unit> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(1000)
                return@runBlocking SuspendResult.Success(Unit)
            }
        }
}