package io.github.gustavobarbosab.favorite_movies

import android.os.Bundle
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.favorite_movies.databinding.FragmentFavoriteMoviesBinding
import io.github.gustavobarbosab.movies.extension.applicationToolbar

class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>() {

    override val layoutId: Int = R.layout.fragment_favorite_movies

    override fun initializeViews(savedInstance: Bundle?) {
        applicationToolbar {
            title = "Favoritos"
        }
    }
}