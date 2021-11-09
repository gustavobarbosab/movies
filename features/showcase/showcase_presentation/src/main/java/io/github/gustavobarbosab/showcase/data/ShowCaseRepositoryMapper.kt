package io.github.gustavobarbosab.showcase.data

import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMovieResponse
import io.github.gustavobarbosab.core.domain.Mapper
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.showcase.model.MovieShowCase

class ShowCaseRepositoryMapper(
    private val configWrapper: AppConfigWrapper
) : Mapper<PopularMovieResponse, MovieShowCase> {

    override fun map(input: PopularMovieResponse): MovieShowCase =
        MovieShowCase(
            input.id,
            input.title,
            input.overview,
            configWrapper.formatImageUrl(input.posterPath),
            configWrapper.formatImageUrl(input.backdropPath),
            false
        )
}