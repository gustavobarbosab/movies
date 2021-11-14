package io.github.gustavobarbosab.favorite.model

data class FavoriteModel(
    val id: Long,
    val imageUrl: String,
    val movieName: String,
    val savedDate: String
)