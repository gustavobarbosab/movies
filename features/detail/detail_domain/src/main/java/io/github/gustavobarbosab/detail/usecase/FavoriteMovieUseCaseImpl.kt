package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.repository.FavoriteMovieRepository
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.detail.model.MovieUpdate
import io.gustavobarbosab.coroutinesresult.CoroutineResult

class FavoriteMovieUseCaseImpl(
    private val repository: FavoriteMovieRepository
) : FavoriteMovieUseCase {

    override suspend fun updateFavoriteMovie(movie: MovieDetailDomain): CoroutineResult<MovieUpdate> {
        val isMovieFavoriteResult = repository.isMovieFavorite(movie.id)

        if (isMovieFavoriteResult !is CoroutineResult.Success)
            return CoroutineResult.UnknownError()

        val isMovieFavorite = isMovieFavoriteResult.data == true
        if (isMovieFavorite) {
            repository.dislikeMovie(movie)
            return CoroutineResult.Success(MovieUpdate.MovieUnliked)
        }

        repository.likeMovie(movie)
        return CoroutineResult.Success(MovieUpdate.MovieLiked)
    }

    override suspend fun isMovieFavorite(id: Long) = repository.isMovieFavorite(id)
}