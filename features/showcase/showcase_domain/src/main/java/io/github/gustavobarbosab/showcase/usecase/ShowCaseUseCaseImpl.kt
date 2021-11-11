package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.gustavobarbosab.coroutinesresult.SimpleResponse

class ShowCaseUseCaseImpl(private val movieRepository: ShowCaseRepository) : ShowCaseUseCase {

    override suspend fun getPopularMovies(): SimpleResponse<List<MovieShowCase>> =
        movieRepository.getPopularMovies()

    override suspend fun getTopRatedMovies(): SimpleResponse<List<MovieShowCase>> =
        movieRepository.getTopRatedMovies()

    override suspend fun getPlayingNow(): SimpleResponse<List<MovieShowCase>> =
        movieRepository.getPlayingNow()

    override suspend fun getLatestMovies(): SimpleResponse<List<MovieShowCase>> =
        movieRepository.getLatestMovies()
}