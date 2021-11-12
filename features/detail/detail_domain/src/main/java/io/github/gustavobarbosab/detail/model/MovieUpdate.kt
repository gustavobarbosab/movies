package io.github.gustavobarbosab.detail.model

sealed class MovieUpdate {
    object MovieLiked : MovieUpdate()
    object MovieUnliked : MovieUpdate()
}
