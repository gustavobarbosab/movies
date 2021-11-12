package io.github.gustavobarbosab.movies.detail.data.favorite

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.detail.repository.FavoriteMovieRepository
import io.gustavobarbosab.coroutinesresult.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor() : FavoriteMovieRepository {

    override suspend fun isMovieFavorite(id: Long): CoroutineResult<Boolean> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(3000)
                var ret = CoroutineResult.Success(false)
                return@runBlocking ret
            }
        }


    override suspend fun likeMovie(movie: MovieDetailDomain): CoroutineResult<Unit> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(3000)
                return@runBlocking CoroutineResult.Success(Unit)
            }
        }

    override suspend fun dislikeMovie(movie: MovieDetailDomain): CoroutineResult<Unit> =
        withContext(Dispatchers.IO) {
            runBlocking {
                delay(3000)
                return@runBlocking CoroutineResult.Success(Unit)
            }
        }
}