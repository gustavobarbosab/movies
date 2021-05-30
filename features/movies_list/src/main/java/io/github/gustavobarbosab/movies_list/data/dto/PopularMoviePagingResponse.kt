package io.github.gustavobarbosab.movies_list.data.dto

import com.squareup.moshi.Json

data class PopularMoviePagingResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<PopularMovieResponse>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)