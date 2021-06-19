package io.github.gustavobarbosab.core.data.network.services.movies

import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMovieResponse
import io.github.gustavobarbosab.core.domain.Mapper
import io.github.gustavobarbosab.core.domain.model.Movie

class MovieMapper(
    private val configWrapper: AppConfigWrapper
) : Mapper<PopularMovieResponse, Movie> {

    override fun map(input: PopularMovieResponse): Movie =
        Movie(
            input.id,
            input.title,
            input.overview,
            configWrapper.formatImageUrl(input.posterPath),
            configWrapper.formatImageUrl(input.backdropPath)
        )
}