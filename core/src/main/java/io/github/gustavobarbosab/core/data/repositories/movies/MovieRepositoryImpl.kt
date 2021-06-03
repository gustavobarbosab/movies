package io.github.gustavobarbosab.core.data.repositories.movies

import android.content.res.Resources
import io.github.gustavobarbosab.core.data.database.movie.MovieLocalDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.MovieMapper
import io.github.gustavobarbosab.core.data.network.services.movies.MovieRemoteDataSource
import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMovieResponse
import io.github.gustavobarbosab.core.domain.Result
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.core.domain.repository.MovieRepository
import retrofit2.Response
import java.lang.Exception

class MovieRepositoryImpl(
    val localDataSource: MovieLocalDataSource,
    val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    private val mapper = MovieMapper()

    override suspend fun getPopularMovies(): Result<List<Movie>> =
        try {
            val result = mapToMovie(remoteDataSource.getPopularMovies().results)
            Result.Success(result)
        } catch (ex: Exception) {
            Result.Error(ex)
        }


    private fun mapToMovie(movieResponse: List<PopularMovieResponse>?): List<Movie> =
        movieResponse
            ?.map(mapper::map)
            ?: emptyList()

}