package io.github.gustavobarbosab.favorite.presentation

import android.os.Bundle
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.movies.favorite.databinding.FragmentFavoriteMoviesBinding
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.favorite.R

class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>() {

    override val layoutId: Int = R.layout.fragment_favorite_movies

    override fun initializeViews(savedInstance: Bundle?) {
        applicationToolbar {
            title = "Favoritos"
        }
    }
}