package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.gustavobarbosab.coroutinesresult.CoroutineResult

class ShowCaseUseCaseImpl(private val movieRepository: ShowCaseRepository) : ShowCaseUseCase {

    override suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>> =
        movieRepository.getPopularMovies()

    override suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>> =
        movieRepository.getTopRatedMovies()

    override suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>> =
        movieRepository.getPlayingNow()

    override suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>> =
        movieRepository.getLatestMovies()
}