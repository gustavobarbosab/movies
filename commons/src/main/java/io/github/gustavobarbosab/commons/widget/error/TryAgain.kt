package io.github.gustavobarbosab.commons.widget.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import io.github.gustavobarbosab.commons.R

class TryAgain @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tvMessage: TextView
    private val btTryAgain: Button
    private var btListener = {}

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.component_try_again, this, true)
        tvMessage = findViewById(R.id.error_title)
        btTryAgain = findViewById(R.id.error_button)
        btTryAgain.setOnClickListener { btListener() }
    }

    fun setMessage(message: String) {
        tvMessage.text = message
    }

    fun configButton(text: Int, listener: () -> Unit) {
        btTryAgain.setText(text)
        btListener = listener
    }

    fun configButton(text: String, listener: () -> Unit) {
        btTryAgain.text = text
        btListener = listener
    }
}