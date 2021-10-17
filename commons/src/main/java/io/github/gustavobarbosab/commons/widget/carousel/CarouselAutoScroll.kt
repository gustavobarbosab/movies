package io.github.gustavobarbosab.commons.widget.carousel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager2.widget.ViewPager2

class CarouselAutoScroll(
    private var viewPager: ViewPager2?,
    private var owner: LifecycleOwner?
) : LifecycleObserver, ViewPager2.OnPageChangeCallback() {

    var onPageChangedListener: (Int) -> Unit = {}
    private var timeMillis = DEFAULT_TIME
    private val handler = Handler(Looper.getMainLooper())

    private val currentPosition: Int
        get() = viewPager?.currentItem ?: FIRST_POSITION

    private val lastPosition: Int
        get() = (viewPager?.adapter?.itemCount ?: 0) - 1

    private val nextPosition: Int
        get() {
            return if (currentPosition < lastPosition) currentPosition + 1
            else FIRST_POSITION
        }

    init {
        owner?.lifecycle?.addObserver(this)
        viewPager?.registerOnPageChangeCallback(this)
    }

    private fun startAutoScroll() {
        val runnable = Runnable {
            viewPager?.setCurrentItem(nextPosition, true)
        }
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(runnable, timeMillis)
    }

    override fun onPageSelected(position: Int) {
        startAutoScroll()
        onPageChangedListener(currentPosition)
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
        const val DEFAULT_TIME = 5000L

        fun setupWithViewPager(
            viewPager: ViewPager2?,
            owner: LifecycleOwner?
        ) = CarouselAutoScroll(viewPager, owner)
    }
}