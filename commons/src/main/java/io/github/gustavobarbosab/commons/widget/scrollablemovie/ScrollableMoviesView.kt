package io.github.gustavobarbosab.commons.widget.scrollablemovie

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import io.github.gustavobarbosab.commons.databinding.ComponentScrollableMoviesBinding
import io.github.gustavobarbosab.commons.extension.toast

class ScrollableMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ComponentScrollableMoviesBinding =
        ComponentScrollableMoviesBinding
            .inflate(
                LayoutInflater.from(context),
                this,
                true
            )

    var adapter: ScrollableMovieAdapter
    var clickListener: (MovieScrollableModel) -> Unit = {}

    init {
        adapter = ScrollableMovieAdapter(this::onMovieClicked)
        binding.movies.adapter = adapter
    }

    fun loadMovies(title: String, movies: List<MovieScrollableModel>) {
        hideProgress()
        binding.moviesTitle.text = title
        adapter.movies = movies
    }

    private fun onMovieClicked(movie: MovieScrollableModel) {
        clickListener(movie)
    }

    fun showProgress() {
        context.toast("Carregando lista")
    }

    fun hideProgress() {
        context.toast("Lista carregada")
    }
}