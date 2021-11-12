package io.github.gustavobarbosab.detail.usecase

import io.github.gustavobarbosab.detail.repository.FavoriteMovieRepository
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.gustavobarbosab.coroutinesresult.CoroutineResult

class FavoriteMovieUseCaseImpl(
    private val repository: FavoriteMovieRepository
) : FavoriteMovieUseCase {

    override suspend fun favoriteMovie(movie: MovieDetailDomain) = repository.likeMovie(movie)

    override suspend fun isMovieFavorite(id: Long) = repository.isMovieFavorite(id)

}