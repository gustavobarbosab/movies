package io.github.gustavobarbosab.showcase.domain.mapper

import io.github.gustavobarbosab.core.domain.Mapper
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase

class MovieShowCaseMapper : Mapper<Movie, MovieShowCase> {
    override fun map(input: Movie): MovieShowCase =
        MovieShowCase(
            input.id,
            input.name,
            input.description,
            input.imageUrl,
            input.isFavorite
        )
}