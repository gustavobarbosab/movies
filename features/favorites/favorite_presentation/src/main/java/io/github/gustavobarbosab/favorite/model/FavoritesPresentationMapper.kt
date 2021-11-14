package io.github.gustavobarbosab.favorite.model

import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite

class FavoritesPresentationMapper {

    fun map(favoritesModel: FavoriteModel) = MovieFavorite(
        favoritesModel.id,
        favoritesModel.movieName,
        "",
        favoritesModel.imageUrl,
        ""
    )

    fun map(movie: MovieFavorite) = FavoriteModel(
        movie.id,
        movie.poster,
        movie.name,
        movie.savedDate
    )
}