package io.github.gustavobarbosab.detail

data class MovieDetail(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String,
    val favorite: Boolean
)