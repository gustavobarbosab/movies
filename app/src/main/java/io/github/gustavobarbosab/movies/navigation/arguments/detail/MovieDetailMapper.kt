package io.github.gustavobarbosab.movies.navigation.arguments.detail

import io.github.gustavobarbosab.core.contracts.Mapper
import io.github.gustavobarbosab.detail.model.MovieDetail

class MovieDetailMapper : Mapper<MovieDetail, MovieDetailArgument> {
    override fun map(input: MovieDetail) = MovieDetailArgument(
        input.id,
        input.name,
        input.description,
        input.imageUrl,
        input.poster,
        input.favorite
    )
}