package io.github.gustavobarbosab.movies.data.repository

import io.github.gustavobarbosab.movies.data.datasources.local.ShowCaseLocalDataSource
import io.github.gustavobarbosab.movies.data.datasources.remote.ShowCaseRemoteDataSource
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.gustavobarbosab.coroutinesresult.CoroutineResult
import javax.inject.Inject

class ShowCaseRepositoryImpl @Inject constructor(
    private val localDataSource: ShowCaseLocalDataSource,
    private val remoteDataSource: ShowCaseRemoteDataSource,
    private val mapper: ShowCaseRepositoryMapper
) : ShowCaseRepository {

    override suspend fun getPopularMovies(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getPopularMovies().map(mapper::map)

    override suspend fun getTopRatedMovies(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getTopRatedMovies().map(mapper::map)

    override suspend fun getPlayingNow(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getPlayingNow().map(mapper::map)

    override suspend fun getLatestMovies(): CoroutineResult<List<MovieShowCase>> =
        remoteDataSource.getLatestMovies().map(mapper::map)
}