package io.github.gustavobarbosab.movies_list.data

import retrofit2.http.GET

interface MovieApi {

    @GET("/movie/popular")
    suspend fun getPopularMovies()
}