package io.github.gustavobarbosab.showcase.domain

import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.core.result.mapTo
import io.github.gustavobarbosab.showcase.domain.mapper.MovieShowCaseMapper
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase

class ShowCaseUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val sessionRepository: SessionRepository
) : ShowCaseUseCase {

    private val mapper = MovieShowCaseMapper()

    override suspend fun getPopularMovies(): Result<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getPopularMovies)

    override suspend fun getTopRatedMovies(): Result<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getTopRatedMovies)


    override suspend fun getPlayingNow(): Result<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getPlayingNow)

    override suspend fun getLatestMovies(): Result<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getLatestMovies)

    private suspend fun fetchDataAndMerge(fetchResult: suspend () -> Result<List<Movie>>): Result<List<MovieShowCase>> {
        val result = fetchResult()
        val favorites = sessionRepository.favoriteMovies()

        return when (result) {
            is Result.Error -> result
            is Result.Success -> bookmarkFavorites(result, favorites)
        }
    }

    private fun bookmarkFavorites(
        serviceResult: Result.Success<List<Movie>>,
        favorites: Result<List<Movie>>
    ): Result<List<MovieShowCase>> =
        when (favorites) {
            is Result.Error -> serviceResult.mapTo { it.map(mapper::map) }
            is Result.Success -> mergeFavoritesAndService(serviceResult, favorites)
        }

    private fun mergeFavoritesAndService(
        serviceResult: Result.Success<List<Movie>>,
        favoriteResult: Result.Success<List<Movie>>
    ): Result<List<MovieShowCase>> =
        favoriteResult.mapTo {
            val favoriteSet = it.toMutableSet()
            favoriteSet.addAll(serviceResult.data)
            return@mapTo favoriteSet.map(mapper::map)
        }
}