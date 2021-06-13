package io.github.gustavobarbosab.core.data.repositories.movies

import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.database.movie.MovieLocalDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.MovieMapper
import io.github.gustavobarbosab.core.data.network.services.movies.MovieRemoteDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMovieResponse
import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository

class MovieRepositoryImpl(
    val localDataSource: MovieLocalDataSource,
    val remoteDataSource: MovieRemoteDataSource,
    val mapper: MovieMapper
) : MovieRepository {

    var localCache: List<Movie> = emptyList()

    override suspend fun getPopularMovies(): Result<List<Movie>> =
        try {
            if (localCache.isEmpty()) {
                localCache = mapToMovie(remoteDataSource.getPopularMovies().results)
            }

            Result.Success(localCache)
        } catch (ex: Exception) {
            Result.Error(ex)
        }


    private fun mapToMovie(movieResponse: List<PopularMovieResponse>?): List<Movie> =
        movieResponse
            ?.map(mapper::map)
            ?: emptyList()

}