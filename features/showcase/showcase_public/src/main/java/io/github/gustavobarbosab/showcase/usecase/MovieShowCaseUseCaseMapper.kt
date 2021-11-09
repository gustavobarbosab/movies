package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.showcase.model.MovieShowCase

class MovieShowCaseUseCaseMapper {

    fun map(input: Movie): MovieShowCase =
        MovieShowCase(
            input.id,
            input.name,
            input.description,
            input.imageUrl,
            input.poster,
            input.isFavorite
        )
}