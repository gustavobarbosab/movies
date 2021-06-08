package io.github.gustavobarbosab.favorite_movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.movies.extension.requireMainActivity

class FavoriteMoviesFragment: Fragment(R.layout.fragment_favorite_movies) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireMainActivity().toolbar.apply {
            setTitle("Favoritos")
        }
    }
}