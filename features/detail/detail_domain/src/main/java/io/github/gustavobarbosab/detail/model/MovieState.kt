package io.github.gustavobarbosab.detail.model

sealed class MovieState {
    object MovieLiked : MovieState()
    object MovieUnliked : MovieState()
}
