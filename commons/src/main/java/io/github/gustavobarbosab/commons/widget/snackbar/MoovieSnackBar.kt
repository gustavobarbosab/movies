package io.github.gustavobarbosab.commons.widget.snackbar

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.commons.R

fun FragmentActivity.showSnackBar(type: SnackBarType, message: String) {
    val view = findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    val snackBarLayout = snackbar.view as Snackbar.SnackbarLayout
    snackBarLayout.setBackgroundColor(
        ContextCompat.getColor(
            this,
            android.R.color.transparent
        )
    )
    val snackText = snackBarLayout.findViewById<TextView>(
        com.google.android.material.R.id.snackbar_text
    )
    snackText.visibility = View.INVISIBLE

    val newLayout = LayoutInflater.from(this)
        .inflate(
            R.layout.component_snackbar,
            snackBarLayout,
            false
        )

    val text = newLayout.findViewById<TextView>(R.id.snack_message)
    val image = newLayout.findViewById<ImageView>(R.id.snack_icon)
    val background = newLayout.findViewById<ConstraintLayout>(R.id.snack_background)

    text.text = message
    image.setImageResource(type.imageResource)
    background.setBackgroundColor(ContextCompat.getColor(this, type.color))

    snackBarLayout.setPadding(0, 0, 0, 0)
    snackBarLayout.addView(newLayout, 0)
    snackbar.show()
}
