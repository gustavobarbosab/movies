package io.github.gustavobarbosab.showcase.domain

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.showcase.domain.mapper.MovieShowCaseMapper
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowCaseUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val sessionRepository: SessionRepository
) : ShowCaseUseCase {

    private val mapper = MovieShowCaseMapper()

    override suspend fun getPopularMovies(): Result<List<MovieShowCase>> =
        withContext(Dispatchers.IO) {
            val popular = movieRepository.getPopularMovies()
            val favorites = sessionRepository.favoriteMovies()

            return@withContext when (popular) {
                is Result.Error -> popular
                is Result.Success -> bookmarkFavorites(popular, favorites)
            }
        }

    override suspend fun getTopRatedMovies(): Result<List<MovieShowCase>> =
        withContext(Dispatchers.IO) {
            val topRated = movieRepository.getTopRatedMovies()
            val favorites = sessionRepository.favoriteMovies()

            return@withContext when (topRated) {
                is Result.Error -> topRated
                is Result.Success -> bookmarkFavorites(topRated, favorites)
            }
        }

    override suspend fun getPlayingNow(): Result<List<MovieShowCase>> =
        withContext(Dispatchers.IO) {
            val playing = movieRepository.getPlayingNow()
            val favorites = sessionRepository.favoriteMovies()

            return@withContext when (playing) {
                is Result.Error -> playing
                is Result.Success -> bookmarkFavorites(playing, favorites)
            }
        }

    override suspend fun getLatestMovies(): Result<List<MovieShowCase>> =
        withContext(Dispatchers.IO) {
            return@withContext when (val movies = movieRepository.getLatestMovies()) {
                is Result.Error -> movies
                is Result.Success -> mapList(movies.data)
            }
        }

    private fun bookmarkFavorites(
        popular: Result.Success<List<Movie>>,
        favorites: Result<List<Movie>>
    ): Result<List<MovieShowCase>> =
        when (favorites) {
            is Result.Error -> mapList(popular.data)
            is Result.Success -> updatePopularMovies(popular, favorites)
        }

    private fun updatePopularMovies(
        popular: Result.Success<List<Movie>>,
        favorites: Result.Success<List<Movie>>
    ): Result<List<MovieShowCase>> {
        popular
            .data
            .forEach {
                if (favorites.data.contains(it)) {
                    it.setFavorite()
                }
            }
        return mapList(popular.data)
    }

    private fun mapList(list: List<Movie>) =
        Result.Success(
            list.map(mapper::map)
        )
}