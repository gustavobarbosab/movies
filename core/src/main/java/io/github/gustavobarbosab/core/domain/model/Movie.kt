package io.github.gustavobarbosab.core.domain.model

data class Movie(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String
) {
    var isFavorite: Boolean = false

    fun setFavorite() {
        isFavorite = true
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Movie)
            return false

        return this.id == other.id
    }
}
