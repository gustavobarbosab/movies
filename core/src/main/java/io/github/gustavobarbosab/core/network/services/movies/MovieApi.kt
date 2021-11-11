package io.github.gustavobarbosab.core.network.services.movies

import io.github.gustavobarbosab.core.network.services.movies.dto.PopularMoviePagingResponse
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviePagingResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): PopularMoviePagingResponse

    @GET("movie/now_playing")
    suspend fun getPlayingNow(): PopularMoviePagingResponse

    @GET("movie/upcoming")
    suspend fun getLatestMovies(): PopularMoviePagingResponse
}