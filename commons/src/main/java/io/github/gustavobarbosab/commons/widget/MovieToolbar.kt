package io.github.gustavobarbosab.commons.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import io.github.gustavobarbosab.commons.R

class MovieToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val image: ImageView
    private val backButton: ImageView
    private val shortcutMenuIcon: ImageView
    private val title: TextView
    private var backButtonListener: (() -> Unit)? = null

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.toolbar_movie, this, true)

        image = findViewById(R.id.toolbarIcon)
        backButton = findViewById(R.id.backIcon)
        shortcutMenuIcon = findViewById(R.id.shortcutIcon)
        title = findViewById(R.id.titleText)
    }

    fun showBackButton(show: Boolean) {
        backButton.isVisible = show
    }

    fun setupBackButton(function: () -> Unit) {
        backButtonListener = function
        backButton.setOnClickListener { backButtonListener?.invoke() }
    }

    fun setTitle(message: String) {
        image.visibility = View.GONE
        title.visibility = View.VISIBLE
        title.text = message
    }

    fun setShortcutListener(action: () -> Unit) {
        shortcutMenuIcon.setOnClickListener { action() }
    }

    fun setShortcutIcon(image: Int) {
        shortcutMenuIcon.apply {
            visibility = View.VISIBLE
            shortcutMenuIcon.setImageResource(image)
        }
    }

    fun setShortcutIcon(image: Drawable?) {
        shortcutMenuIcon.apply {
            visibility = View.VISIBLE
            setImageDrawable(image)
        }
    }

    fun showShortcut(show: Boolean) {
        shortcutMenuIcon.isVisible = show
    }

    fun setLogo(icon: Int = R.drawable.ic_default_icon) {
        title.visibility = View.GONE
        image.visibility = View.VISIBLE
        image.setImageResource(icon)
    }

    fun removeListener() = apply {
        backButtonListener = null
    }

    class Builder {
        private var builderShowShortcut = false
        private var builderShowBackButton = false
        private var builderShortcutIcon = 0
        private var builderShortcutListener = {}
        private var builderTitle: String = ""
        private var builderLogo: Int = 0

        private fun isResourceValid(res: Int) = res != 0

        fun shortcut(show: Boolean) = apply {
            builderShowShortcut = show
        }

        fun backButton(show: Boolean) = apply {
            builderShowBackButton = show
        }

        fun shortcutIcon(icon: Int) = apply {
            builderShortcutIcon = icon
        }

        fun shortcutListener(listener: () -> Unit) = apply {
            builderShortcutListener = listener
        }

        fun title(title: String) = apply {
            builderTitle = title
        }

        fun logo(icon: Int) = apply {
            builderLogo = icon
        }

        fun build(toolbar: MovieToolbar) = with(toolbar) {
            showShortcut(builderShowShortcut)
            showBackButton(builderShowBackButton)
            builderShortcutIcon.takeIf(this@Builder::isResourceValid)?.let { setShortcutIcon(it) }
            setShortcutListener(builderShortcutListener)
            setTitle(builderTitle)
            builderLogo.takeIf(this@Builder::isResourceValid)?.let { setLogo(it) }
        }
    }
}