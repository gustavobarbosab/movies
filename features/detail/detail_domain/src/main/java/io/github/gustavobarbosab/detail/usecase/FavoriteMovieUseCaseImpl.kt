package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.repository.FavoriteMovieRepository
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.detail.model.MovieState
import io.gustavobarbosab.suspendresult.SuspendResult

class FavoriteMovieUseCaseImpl(
    private val repository: FavoriteMovieRepository
) : FavoriteMovieUseCase {

    override suspend fun updateFavoriteMovie(movie: MovieDetailDomain): SuspendResult<MovieState> {
        val isMovieFavoriteResult = repository.isMovieFavorite(movie.id)

        if (isMovieFavoriteResult !is SuspendResult.Success)
            return SuspendResult.UnknownError()

        val isMovieFavorite = isMovieFavoriteResult.data == true
        if (isMovieFavorite) {
            repository.dislikeMovie(movie)
            return SuspendResult.Success(MovieState.MovieUnliked)
        }

        repository.likeMovie(movie)
        return SuspendResult.Success(MovieState.MovieLiked)
    }

    override suspend fun isMovieFavorite(id: Long): SuspendResult<MovieState> {
        val result = repository.isMovieFavorite(id)
        return when {
            result is SuspendResult.Success && result.data == true -> SuspendResult.Success(MovieState.MovieLiked)
            result is SuspendResult.Success -> SuspendResult.Success(MovieState.MovieUnliked)
            else -> SuspendResult.UnknownError()
        }
    }
}