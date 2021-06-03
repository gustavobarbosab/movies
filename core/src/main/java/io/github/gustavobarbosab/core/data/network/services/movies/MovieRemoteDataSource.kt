package io.github.gustavobarbosab.core.data.network.services.movies

class MovieRemoteDataSource(private val movieApi: MovieApi) {
    suspend fun getPopularMovies() = movieApi.getPopularMovies()
}