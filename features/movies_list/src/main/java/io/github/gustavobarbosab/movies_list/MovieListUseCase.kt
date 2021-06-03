package io.github.gustavobarbosab.movies_list

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieListUseCase(
    private val movieRepository: MovieRepository,
    private val sessionRepository: SessionRepository
) : MovieUseCase {

    override suspend fun getPopularMovies(): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            val popular = movieRepository.getPopularMovies()
            val favorites = sessionRepository.favoriteMovies()

            return@withContext when (popular) {
                is Result.Error -> popular
                is Result.Success -> bookmarkFavorites(popular, favorites)
            }
        }

    private fun bookmarkFavorites(
        popular: Result.Success<List<Movie>>,
        favorites: Result<List<Movie>>
    ): Result<List<Movie>> =
        when (favorites) {
            is Result.Error -> popular
            is Result.Success -> updatePopularMovies(popular, favorites)
        }

    private fun updatePopularMovies(
        popular: Result.Success<List<Movie>>,
        favorites: Result.Success<List<Movie>>
    ): Result.Success<List<Movie>> {
        popular
            .data
            .forEach {
                if (favorites.data.contains(it)) {
                    it.setFavorite()
                }
            }
        return popular
    }
}