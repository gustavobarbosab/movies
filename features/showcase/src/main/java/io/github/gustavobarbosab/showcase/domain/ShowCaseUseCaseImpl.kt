package io.github.gustavobarbosab.showcase.domain

import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.showcase.domain.mapper.MovieShowCaseMapper
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.extensions.mapCoroutineResult
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult

class ShowCaseUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val sessionRepository: SessionRepository
) : ShowCaseUseCase {

    private val mapper = MovieShowCaseMapper()

    override suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getPopularMovies)

    override suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getTopRatedMovies)

    override suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getPlayingNow)

    override suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getLatestMovies)

    private suspend fun fetchDataAndMerge(fetchResult: suspend () -> CoroutineResult<List<Movie>>): CoroutineResult<List<MovieShowCase>> {
        val result = fetchResult()
        val favorites = sessionRepository.favoriteMovies()

        return when (result) {
            is CoroutineResult.Error -> result
            is CoroutineResult.Success -> bookmarkFavorites(result, favorites)
        }
    }

    private fun bookmarkFavorites(
        serviceResult: CoroutineResult.Success<List<Movie>>,
        favorites: CoroutineResult<List<Movie>>
    ): CoroutineResult<List<MovieShowCase>> =
        when (favorites) {
            is CoroutineResult.Error -> serviceResult.mapCoroutineResult { it.map(mapper::map) }
            is CoroutineResult.Success -> mergeFavoritesAndService(serviceResult, favorites)
        }

    private fun mergeFavoritesAndService(
        serviceResult: CoroutineResult.Success<List<Movie>>,
        favoriteResult: CoroutineResult.Success<List<Movie>>
    ): CoroutineResult<List<MovieShowCase>> =
        favoriteResult.mapCoroutineResult {
            val favoriteSet = it.toMutableSet()
            favoriteSet.addAll(serviceResult.data)
            return@mapCoroutineResult favoriteSet.map(mapper::map)
        }
}