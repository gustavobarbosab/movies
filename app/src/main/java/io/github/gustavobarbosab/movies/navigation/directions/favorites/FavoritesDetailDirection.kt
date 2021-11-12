package io.github.gustavobarbosab.movies.navigation.directions.favorites

import androidx.navigation.NavDirections
import io.github.gustavobarbosab.detail.model.MovieDetail
import io.github.gustavobarbosab.favorite_movies.FavoriteMoviesFragmentDirections
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailMapper
import io.github.gustavobarbosab.movies.navigation.directions.DirectionAdapter

class FavoritesDetailDirection(val detail: MovieDetail) : DirectionAdapter {

    private var mapper = MovieDetailMapper()

    override fun createDirection(): NavDirections {
        val argument = mapper.map(detail)
        return FavoriteMoviesFragmentDirections.actionDetailFragment(argument)
    }
}