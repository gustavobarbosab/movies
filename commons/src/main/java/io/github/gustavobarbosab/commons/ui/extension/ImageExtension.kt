package io.github.gustavobarbosab.commons.ui.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide
        .with(context)
        .load(url)
        .centerInside()
        .into(this)
}