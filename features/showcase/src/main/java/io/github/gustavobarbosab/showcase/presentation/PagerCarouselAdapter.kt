package io.github.gustavobarbosab.showcase.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.showcase.databinding.ItemPagerCarouselBinding
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase

class PagerCarouselAdapter(
    private val clickListener: (MovieShowCase) -> Unit
) : RecyclerView.Adapter<PagerCarouselAdapter.ViewHolder>() {

    var items: List<MovieShowCase> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemPagerCarouselBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val binding: ItemPagerCarouselBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: MovieShowCase,
            clickListener: (MovieShowCase) -> Unit
        ) {
            binding.apply {
                root.setOnClickListener { clickListener(movie) }
                carouselImage.loadImage(movie.posterUrl)
                movieName.text = movie.name
            }
        }
    }
}