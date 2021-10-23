package io.github.gustavobarbosab.commons.widget.scrollablemovie

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout
import io.github.gustavobarbosab.commons.R
import io.github.gustavobarbosab.commons.databinding.ComponentScrollableMoviesBinding

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

    private var shimmerLayout = rootView.findViewById<ShimmerFrameLayout>(R.id.shimmer_layout)

    var adapter: ScrollableMovieAdapter
    var clickListener: (MovieScrollableModel) -> Unit = {}

    init {
        adapter = ScrollableMovieAdapter(this::onMovieClicked)
        binding.movies.adapter = adapter
    }

    fun showShimmer() {
        shimmerLayout.startShimmer()
        shimmerLayout.isVisible = true
        binding.movies.isVisible = false
    }

    fun hideShimmer() {
        shimmerLayout.stopShimmer()
        shimmerLayout.isVisible = false
        binding.movies.isVisible = true
    }

    fun loadMovies(title: String, movies: List<MovieScrollableModel>) {
        hideShimmer()
        binding.moviesTitle.text = title
        adapter.movies = movies
    }

    private fun onMovieClicked(movie: MovieScrollableModel) {
        clickListener(movie)
    }
}