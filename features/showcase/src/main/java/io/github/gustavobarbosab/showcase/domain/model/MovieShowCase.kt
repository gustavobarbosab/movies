package io.github.gustavobarbosab.showcase.domain.model

import io.github.gustavobarbosab.commons.widget.scrollablemovie.MovieScrollableModel

data class MovieShowCase(
    override val id: Long,
    val name: String,
    val description: String,
    override val imageUrl: String,
    val posterUrl: String,
    override val isFavorite: Boolean
) : MovieScrollableModel