package io.github.gustavobarbosab.movies.navigation.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieDetailArg(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String,
    val favorite: Boolean
): Parcelable