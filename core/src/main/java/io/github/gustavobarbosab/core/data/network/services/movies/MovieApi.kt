package io.github.gustavobarbosab.core.data.network.services.movies

import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMoviePagingResponse
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviePagingResponse
}