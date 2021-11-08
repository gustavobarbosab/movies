package io.github.gustavobarbosab.showcase.data

import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMoviePagingResponse
import io.github.gustavobarbosab.showcase.data.datasources.local.ShowCaseLocalDataSource
import io.github.gustavobarbosab.showcase.data.datasources.remote.ShowCaseRemoteDataSource
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.gustavobarbosab.coroutinesresult.extensions.mapCoroutineResult
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository

class ShowCaseRepositoryImpl(
    private val localDataSource: ShowCaseLocalDataSource,
    private val remoteDataSource: ShowCaseRemoteDataSource,
    private val mapper: ShowCaseRepositoryMapper
) : ShowCaseRepository {

    private fun mapToMovie(response: PopularMoviePagingResponse): List<MovieShowCase> =
        response.results.map(mapper::map)

    override suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getPopularMovies().mapCoroutineResult(this::mapToMovie)

    override suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getTopRatedMovies().mapCoroutineResult(this::mapToMovie)

    override suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getPlayingNow().mapCoroutineResult(this::mapToMovie)

    override suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getLatestMovies().mapCoroutineResult(this::mapToMovie)

}