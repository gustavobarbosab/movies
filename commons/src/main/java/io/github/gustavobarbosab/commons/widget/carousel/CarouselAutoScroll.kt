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
    private var lastRunnable: Runnable? = null

    init {
        currentPosition = viewPager?.currentItem ?: FIRST_POSITION
        owner?.lifecycle?.addObserver(this)
        viewPager?.registerOnPageChangeCallback(this)
        executeAutoScroll()
    }

    private val runnableToScroll
        get() = Runnable {
            if (viewPager == null)
                return@Runnable

            currentPosition =
                if (currentPosition < totalItems) currentPosition + 1
                else FIRST_POSITION

            viewPager?.setCurrentItem(currentPosition, true)
            executeAutoScroll()
        }

    private fun executeAutoScroll() {
        lastRunnable = runnableToScroll
        viewPager?.postDelayed(lastRunnable, timeMillis)
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (state != ViewPager2.SCROLL_STATE_DRAGGING)
            return
        viewPager?.removeCallbacks(lastRunnable)
        currentPosition = viewPager?.currentItem ?: FIRST_POSITION
        executeAutoScroll()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        viewPager?.unregisterOnPageChangeCallback(this)
        owner?.lifecycle?.removeObserver(this)
        viewPager?.removeCallbacks(lastRunnable)
        owner = null
        viewPager = null
    }

    companion object {
        const val FIRST_POSITION = 0
        const val DEFAULT_TIME = 5000L

        fun setupWithViewPager(
            viewPager: ViewPager2?,
            owner: LifecycleOwner?
        ) = CarouselAutoScroll(viewPager, owner)
    }
}