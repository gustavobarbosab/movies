package io.github.gustavobarbosab.showcase

import io.github.gustavobarbosab.core.domain.model.Movie

object ShowCaseMocker {
    fun createMovie(
        id: Long = DEFAULT_ID,
        name: String = DEFAULT_NAME,
        description: String = DEFAULT_DESCRIPTION,
        image: String = DEFAULT_IMAGE,
        poster: String = DEFAULT_POSTER,
        favorite: Boolean = false
    ) = Movie(
        id,
        name,
        description,
        image,
        poster
    ).also { it.isFavorite = favorite }


    const val DEFAULT_ID = 1L
    const val DEFAULT_NAME = "Gustavo"
    const val DEFAULT_DESCRIPTION = "description"
    const val DEFAULT_IMAGE = "image"
    const val DEFAULT_POSTER = "poster"
}