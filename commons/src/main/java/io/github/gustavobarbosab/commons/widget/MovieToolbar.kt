package io.github.gustavobarbosab.commons.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import io.github.gustavobarbosab.commons.R

class MovieToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    protected val imageLogo: ImageView
    protected val backButton: ImageButton
    protected val shortcutMenuIcon: ImageButton
    protected val title: TextView

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.toolbar_movie, this)

        imageLogo = findViewById(R.id.toolbarIcon)
        backButton = findViewById(R.id.backIcon)
        shortcutMenuIcon = findViewById(R.id.shortcutIcon)
        title = findViewById(R.id.titleText)
    }

    fun showBackButton() {
        backButton.visibility = View.VISIBLE
    }

    fun hideBackButton() {
        backButton.visibility = View.GONE
    }

    fun setupBackButton(function: () -> Unit) {
        showBackButton()
        backButton.setOnClickListener { function() }
    }

    fun setTitle(message: String) {
        imageLogo.visibility = View.GONE
        title.visibility = View.VISIBLE
        title.text = message
    }

    fun setLogo() {
        imageLogo.visibility = View.VISIBLE
        title.visibility = View.GONE
    }
}