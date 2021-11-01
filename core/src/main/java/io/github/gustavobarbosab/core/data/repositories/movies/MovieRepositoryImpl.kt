package io.github.gustavobarbosab.core.data.repositories.movies

import io.github.gustavobarbosab.core.data.database.movie.MovieLocalDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.MovieMapper
import io.github.gustavobarbosab.core.data.network.services.movies.MovieRemoteDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMoviePagingResponse
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.gustavobarbosab.coroutinesresult.extensions.mapCoroutineResult
import io.gustavobarbosab.coroutinesresult.model.CoroutineResult

class MovieRepositoryImpl(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
    private val mapper: MovieMapper
) : MovieRepository {

    private fun mapToMovie(response: PopularMoviePagingResponse): List<Movie> =
        response.results.map(mapper::map)

    override suspend fun getPopularMovies(): CoroutineResult<List<Movie>> =
        remoteDataSource.getPopularMovies().mapCoroutineResult(this::mapToMovie)

    override suspend fun getTopRatedMovies(): CoroutineResult<List<Movie>> =
        remoteDataSource.getTopRatedMovies().mapCoroutineResult(this::mapToMovie)

    override suspend fun getPlayingNow(): CoroutineResult<List<Movie>> =
        remoteDataSource.getPlayingNow().mapCoroutineResult(this::mapToMovie)

    override suspend fun getLatestMovies(): CoroutineResult<List<Movie>> =
        remoteDataSource.getLatestMovies().mapCoroutineResult(this::mapToMovie)

}