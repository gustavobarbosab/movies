package io.github.gustavobarbosab.favorite_movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.commons.extension.toolbar

class FavoriteMoviesFragment: Fragment(R.layout.fragment_favorite_movies) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar()?.setTitle("Favoritos")
    }
}