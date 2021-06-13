package io.github.gustavobarbosab.showcase.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.showcase.databinding.ItemMovieListBinding

class MovieListAdapter(val clickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMovieListBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], clickListener)
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(
        private val binding: ItemMovieListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
            clickListener: (Movie) -> Unit
        ) {
            binding.apply {
                binding.root.setOnClickListener { clickListener(movie) }
                movie.imageUrl?.let { movieImage.loadImage(it) }
                movieName.text = movie.name
            }
        }
    }
}