package io.github.gustavobarbosab.showcase.mapper

import io.github.gustavobarbosab.core.domain.Mapper
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.detail.domain.model.MovieDetail
import io.github.gustavobarbosab.showcase.model.MovieShowCase

class MovieShowCaseMapper : Mapper<Movie, MovieShowCase> {
    override fun map(input: Movie): MovieShowCase =
        MovieShowCase(
            input.id,
            input.name,
            input.description,
            input.imageUrl,
            input.poster,
            input.isFavorite
        )

    fun mapToDetail(input: MovieShowCase): MovieDetail = MovieDetail(
        input.id,
        input.name,
        input.description,
        input.imageUrl,
        input.posterUrl,
        input.isFavorite
    )
}