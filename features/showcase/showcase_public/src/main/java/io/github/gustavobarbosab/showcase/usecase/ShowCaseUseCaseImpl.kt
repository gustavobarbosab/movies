package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.core.domain.repository.SessionRepository
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.gustavobarbosab.coroutinesresult.extensions.mapCoroutineResult
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult.*

class ShowCaseUseCaseImpl(
    private val movieRepository: ShowCaseRepository,
    private val sessionRepository: SessionRepository
) : ShowCaseUseCase {

    private val mapper = MovieShowCaseUseCaseMapper()

    override suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getPopularMovies)

    override suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getTopRatedMovies)

    override suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getPlayingNow)

    override suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>> =
        fetchDataAndMerge(movieRepository::getLatestMovies)

    private suspend fun fetchDataAndMerge(
        fetchResult: suspend () -> CoroutineResult<List<MovieShowCase>>
    ): CoroutineResult<List<MovieShowCase>> {
        val result = fetchResult()
        val favorites = sessionRepository.favoriteMovies().mapCoroutineResult {
            it.map(mapper::map)
        }

        return when {
            result is Error -> favorites
            favorites is Error -> result
            else -> mergeFavoritesAndService(
                result as Success<List<MovieShowCase>>,
                favorites as Success<List<MovieShowCase>>
            )
        }
    }

    private fun mergeFavoritesAndService(
        serviceResult: Success<List<MovieShowCase>>,
        favoriteResult: Success<List<MovieShowCase>>
    ): CoroutineResult<List<MovieShowCase>> =
        favoriteResult.mapCoroutineResult {
            val favoriteSet = it.toMutableSet()
            favoriteSet.addAll(serviceResult.data)
            return@mapCoroutineResult favoriteSet.toList()
        }
}