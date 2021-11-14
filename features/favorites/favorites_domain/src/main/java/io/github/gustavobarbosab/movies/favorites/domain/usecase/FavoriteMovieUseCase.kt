package io.github.gustavobarbosab.movies.favorites.domain.usecase


import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieState
import io.gustavobarbosab.suspendresult.SuspendResult

interface FavoriteMovieUseCase {
    //TODO criar mais de um use case
    suspend fun fetchFavorites() : SuspendResult<List<MovieFavorite>>
    suspend fun updateFavoriteMovie(movie: MovieFavorite): SuspendResult<MovieState>
    suspend fun isMovieFavorite(id: Long): SuspendResult<MovieState>
}