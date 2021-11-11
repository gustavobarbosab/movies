package io.github.gustavobarbosab.core.network.services.movies

import io.github.gustavobarbosab.core.network.services.movies.dto.PopularMoviePagingResponse
import io.gustavobarbosab.coroutinesresult.CoroutineResult
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): CoroutineResult<PopularMoviePagingResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): CoroutineResult<PopularMoviePagingResponse>

    @GET("movie/now_playing")
    suspend fun getPlayingNow(): CoroutineResult<PopularMoviePagingResponse>

    @GET("movie/upcoming")
    suspend fun getLatestMovies(): CoroutineResult<PopularMoviePagingResponse>
}