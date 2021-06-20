package io.github.gustavobarbosab.commons.widget.autoprogress

import android.os.CountDownTimer
import android.util.Log

class ProgressCounter(
    val counterListener: (Int) -> Unit,
    val counterTimeMillis: Long,
    val loop: Boolean
) {

    private var countDownTimer: CountDownTimer? = null

    fun startCounter() {
        countDownTimer = object : CountDownTimer(counterTimeMillis, ONE_SECOND_IN_MILLIS) {
            override fun onTick(millisUntilFinished: Long) {
                val timeToFinish = calculate(millisUntilFinished)
                counterListener(timeToFinish)
                Log.d("counter", timeToFinish.toString())
            }

            override fun onFinish() {
                counterListener(ONE_HUNDRED_PERCENT)
                if (loop) startCounter()
                Log.d("counter", "Finalizou")
            }
        }.start()
    }

    private fun calculate(timeToFinish: Long) =
        100 - (timeToFinish / counterTimeMillis.toFloat() * 100).toInt()

    fun stopCounter() = apply {
        countDownTimer?.cancel()
    }

    companion object {
        const val ONE_SECOND_IN_MILLIS = 10L
        const val ONE_HUNDRED_PERCENT = 100
    }
}