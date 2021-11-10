package io.github.gustavobarbosab.movies.navigation.detail

import io.github.gustavobarbosab.detail.MovieDetail

class DetailModelMapper {

    fun map(movieDetail: MovieDetail) =
        MovieDetailArg(
            movieDetail.id,
            movieDetail.name,
            movieDetail.description,
            movieDetail.imageUrl,
            movieDetail.poster,
            movieDetail.favorite
        )
}