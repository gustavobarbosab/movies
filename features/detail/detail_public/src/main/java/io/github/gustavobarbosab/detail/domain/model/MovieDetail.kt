package io.github.gustavobarbosab.detail.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieDetail(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String,
    val favorite: Boolean
) : Parcelable