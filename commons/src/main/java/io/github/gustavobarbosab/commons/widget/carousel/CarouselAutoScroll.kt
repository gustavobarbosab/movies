package io.github.gustavobarbosab.commons.widget.carousel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager2.widget.ViewPager2

class CarouselAutoScroll(
    private var viewPager: ViewPager2?,
    private var owner: LifecycleOwner?
) : LifecycleObserver, ViewPager2.OnPageChangeCallback() {

    private var timeMillis = DEFAULT_TIME
    private var currentPosition = FIRST_POSITION
    private val totalItems: Int
        get() = viewPager?.adapter?.itemCount ?: 0

    init {
        currentPosition = viewPager?.currentItem ?: FIRST_POSITION
        owner?.lifecycle?.addObserver(this)
        viewPager?.registerOnPageChangeCallback(this)
        executeAutoScroll()
    }

    private val runnableToScroll = Runnable {
        if (viewPager == null)
            return@Runnable

        currentPosition =
            if (currentPosition < totalItems) currentPosition + 1
            else FIRST_POSITION

        viewPager?.setCurrentItem(currentPosition, true)

        executeAutoScroll()
    }

    private fun executeAutoScroll() {
        viewPager?.postDelayed(runnableToScroll, timeMillis)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        viewPager?.unregisterOnPageChangeCallback(this)
        owner?.lifecycle?.removeObserver(this)
        owner = null
        viewPager = null
    }

    companion object {
        const val FIRST_POSITION = 0
        const val DEFAULT_TIME = 4000L
    }
}