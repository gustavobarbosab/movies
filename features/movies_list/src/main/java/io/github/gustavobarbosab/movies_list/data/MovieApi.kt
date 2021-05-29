package io.github.gustavobarbosab.movies_list.data

interface MovieApi {
    suspend fun getMovieList()
}