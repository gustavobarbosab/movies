package io.github.gustavobarbosab.movies_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.movies_list.databinding.ItemMovieListBinding

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMovieListBinding.inflate(inflater, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            movieName.text = movies[position].name
        }
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(
        val binding: ItemMovieListBinding
    ) : RecyclerView.ViewHolder(binding.root)
}