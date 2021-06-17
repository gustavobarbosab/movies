package io.github.gustavobarbosab.commons.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.HandlerCompat
import io.github.gustavobarbosab.commons.R

class MovieToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    protected val imageLogo: ImageView
    protected val backButton: ImageView
    protected val shortcutMenuIcon: ImageView
    protected val title: TextView
    private var backButtonListener: (() -> Unit)? = null
    private var handlerToChangeVisibility = Handler()

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.toolbar_movie, this, true)

        imageLogo = findViewById(R.id.toolbarIcon)
        backButton = findViewById(R.id.backIcon)
        shortcutMenuIcon = findViewById(R.id.shortcutIcon)
        title = findViewById(R.id.titleText)
    }

    fun showBackButton() = apply {
        backButton.visibility = View.VISIBLE
    }

    fun hideBackButton() = apply {
        backButton.visibility = View.GONE
    }

    fun setupBackButton(function: () -> Unit) = apply {
        backButtonListener = function
        backButton.setOnClickListener { backButtonListener?.invoke() }
    }

    fun setTitle(message: String) = apply {
        imageLogo.visibility = View.GONE
        title.visibility = View.VISIBLE
        title.text = message
    }

    fun setShortcutListener(action: () -> Unit) = apply {
        shortcutMenuIcon.setOnClickListener { action() }
    }

    fun setShortcutIcon(image: Int) = apply {
        shortcutMenuIcon.apply {
            visibility = View.VISIBLE
            shortcutMenuIcon.setImageResource(image)
        }
    }

    fun setShortcutIcon(image: Drawable?) = apply {
        shortcutMenuIcon.apply {
            visibility = View.VISIBLE
            setImageDrawable(image)
        }
    }

    fun hideShortcutIcon() = apply {
        shortcutMenuIcon.visibility = View.GONE
    }

    fun setLogo() = apply {
        title.visibility = View.GONE
        imageLogo.visibility = View.VISIBLE
    }

    fun removeListener() = apply {
        backButtonListener = null
    }
}