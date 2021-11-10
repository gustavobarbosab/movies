package io.github.gustavobarbosab.showcase.presentation.movielist

import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.commons.widget.scrollablemovie.ScrollableBinding
import io.github.gustavobarbosab.commons.widget.scrollablemovie.ScrollableMovieRecyclerViewAdapter
import io.github.gustavobarbosab.showcase.model.MovieShowCase

class MovieListAdapter(
    val clickListener: (MovieShowCase) -> Unit
) : ScrollableMovieRecyclerViewAdapter() {

    var movieList: List<MovieShowCase> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override val bindingViewHolder: ScrollableBinding = { position ->
        val movie = movieList[position]
        root.setOnClickListener { clickListener(movie) }
        movieImage.loadImage(movie.imageUrl)
    }

    override fun getItemCount(): Int = movieList.size
}