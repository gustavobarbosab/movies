package io.github.gustavobarbosab.movies.navigation.mapper

import io.github.gustavobarbosab.detail.MovieDetail
import io.github.gustavobarbosab.movies.navigation.arguments.MovieDetailArgument

fun MovieDetail.mapToArgument() = MovieDetailArgument(
    this.id,
    this.name,
    this.description,
    this.imageUrl,
    this.poster,
    this.favorite
)