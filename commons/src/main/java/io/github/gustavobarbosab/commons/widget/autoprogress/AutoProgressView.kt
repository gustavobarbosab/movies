package io.github.gustavobarbosab.commons.widget.autoprogress

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar

class AutoProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProgressBar(context, attrs, defStyleAttr) {

    private val counter = ProgressCounter(
        this::updateProgress,
        5000, // TODO parametrizar valor
        true // TODO parametrizar valor
    )

    init {
        max = 100
        progress = 0
    }

    fun startProgress() {
        progress = 0
        counter.startCounter()
    }

    private fun updateProgress(progress: Int) {
        this.progress = progress
    }

    fun stopProgress() {
        counter.stopCounter()
    }
}