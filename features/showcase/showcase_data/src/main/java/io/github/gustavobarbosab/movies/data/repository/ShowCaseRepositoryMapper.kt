package io.github.gustavobarbosab.movies.data.repository

import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.network.services.movies.dto.PopularMovieResponse
import io.github.gustavobarbosab.core.domain.Mapper
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import javax.inject.Inject

class ShowCaseRepositoryMapper @Inject constructor(
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