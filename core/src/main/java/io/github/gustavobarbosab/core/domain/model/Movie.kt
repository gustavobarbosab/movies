package io.github.gustavobarbosab.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

data class Movie(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String
) {
    var isFavorite: Boolean = false
}
