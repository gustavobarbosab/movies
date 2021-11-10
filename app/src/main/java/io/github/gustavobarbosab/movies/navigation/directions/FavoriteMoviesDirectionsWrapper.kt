package io.github.gustavobarbosab.movies.navigation.directions

import androidx.navigation.NavDirections
import io.github.gustavobarbosab.detail.MovieDetail
import io.github.gustavobarbosab.favorite_movies.FavoriteMoviesFragmentDirections
import io.github.gustavobarbosab.movies.navigation.mapper.mapToArgument

object FavoriteMoviesDirectionsWrapper {
    fun actionDetailFragment(detail: MovieDetail): NavDirections =
        FavoriteMoviesFragmentDirections.actionDetailFragment(detail.mapToArgument())
}