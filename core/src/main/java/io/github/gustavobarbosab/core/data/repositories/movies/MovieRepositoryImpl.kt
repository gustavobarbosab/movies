package io.github.gustavobarbosab.core.data.repositories.movies

import io.github.gustavobarbosab.core.data.database.movie.MovieLocalDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.MovieMapper
import io.github.gustavobarbosab.core.data.network.services.movies.MovieRemoteDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMoviePagingResponse
import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import io.github.gustavobarbosab.core.result.mapTo

class MovieRepositoryImpl(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
    private val mapper: MovieMapper
) : MovieRepository {

    private fun mapToMovie(response: PopularMoviePagingResponse): List<Movie> =
        response.results.map(mapper::map)

    override suspend fun getPopularMovies(): Result<List<Movie>> =
        remoteDataSource.getPopularMovies().mapTo(this::mapToMovie)

    override suspend fun getTopRatedMovies(): Result<List<Movie>> =
        remoteDataSource.getTopRatedMovies().mapTo(this::mapToMovie)

    override suspend fun getPlayingNow(): Result<List<Movie>> =
        remoteDataSource.getPlayingNow().mapTo(this::mapToMovie)

    override suspend fun getLatestMovies(): Result<List<Movie>> =
        remoteDataSource.getLatestMovies().mapTo(this::mapToMovie)

}