package io.github.gustavobarbosab.movies_list.data

import io.github.gustavobarbosab.movies_list.data.dto.PopularMoviePagingResponse
import io.github.gustavobarbosab.movies_list.data.dto.PopularMovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<PopularMoviePagingResponse>
}