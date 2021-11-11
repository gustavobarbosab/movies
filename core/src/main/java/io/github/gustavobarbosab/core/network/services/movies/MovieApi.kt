package io.github.gustavobarbosab.core.network.services.movies

import io.github.gustavobarbosab.core.network.services.movies.dto.PopularMoviePagingResponse
import io.gustavobarbosab.coroutinesresult.SimpleResponse
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): SimpleResponse<PopularMoviePagingResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): SimpleResponse<PopularMoviePagingResponse>

    @GET("movie/now_playing")
    suspend fun getPlayingNow(): SimpleResponse<PopularMoviePagingResponse>

    @GET("movie/upcoming")
    suspend fun getLatestMovies(): SimpleResponse<PopularMoviePagingResponse>
}