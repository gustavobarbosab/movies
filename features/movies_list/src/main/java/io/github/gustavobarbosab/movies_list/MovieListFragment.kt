package io.github.gustavobarbosab.movies_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.movies_list.di.DaggerMovieListComponent
import javax.inject.Inject

@ModuleScope
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    @Inject
    lateinit var myStringFromAppComponent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMovieListComponent
            .factory()
            .create(requireAppComponent())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}