package io.github.gustavobarbosab.commons.widget.snackbar

import io.github.gustavobarbosab.commons.R

sealed class SnackBarType(
    val color: Int,
    val imageResource: Int
) {
    object Alert : SnackBarType(R.color.colorShimmer, R.drawable.ic_warning)
    object Success : SnackBarType(R.color.colorShimmer, R.drawable.ic_success)
    object Error : SnackBarType(R.color.colorShimmer, R.drawable.ic_error)
}