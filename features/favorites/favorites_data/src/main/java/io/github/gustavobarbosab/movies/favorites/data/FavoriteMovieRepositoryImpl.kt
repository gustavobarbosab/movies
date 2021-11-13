package io.github.gustavobarbosab.movies.favorites.data

import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.favorites.domain.repository.FavoriteMovieRepository
import io.gustavobarbosab.suspendresult.SuspendResult
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


    override suspend fun likeMovie(movie: MovieFavorite): SuspendResult<Unit> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(1000)
                return@runBlocking SuspendResult.Success(Unit)
            }
        }

    override suspend fun dislikeMovie(movie: MovieFavorite): SuspendResult<Unit> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(1000)
                return@runBlocking SuspendResult.Success(Unit)
            }
        }
}