package io.github.gustavobarbosab.core.domain.model

data class Movie(
    val id: Long,
    val name: String,
    val description: String
) {
    var isFavorite: Boolean = false

    fun setFavorite() {
        isFavorite = true
    }
}
