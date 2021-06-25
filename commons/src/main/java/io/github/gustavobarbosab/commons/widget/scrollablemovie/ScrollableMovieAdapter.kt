package io.github.gustavobarbosab.commons.widget.scrollablemovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.commons.databinding.ItemScrollableMoviesBinding
import io.github.gustavobarbosab.commons.ui.extension.loadImage

class ScrollableMovieAdapter(
    private val clickListener: (MovieScrollableModel) -> Unit
) : RecyclerView.Adapter<ScrollableMovieAdapter.ViewHolder>() {

    var movies: List<MovieScrollableModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemScrollableMoviesBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], clickListener)
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(
        private val binding: ItemScrollableMoviesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: MovieScrollableModel,
            clickListener: (MovieScrollableModel) -> Unit
        ) {
            binding.apply {
                root.setOnClickListener { clickListener(movie) }
                movieImage.loadImage(movie.imageUrl)
                likeMovie.isChecked = movie.isFavorite
            }
        }
    }
}