package io.github.gustavobarbosab.commons.widget.carousel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import io.github.gustavobarbosab.commons.widget.autoprogress.AutoProgressView

class CarouselAutoScroll(
    private var owner: LifecycleOwner?,
    private var viewPager: ViewPager2?,
    private var autoProgressView: AutoProgressView? = null
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
        viewPager?.registerOnPageChangeCallback(this)
        owner?.lifecycle?.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    onDestroy()
                }
            }
        })
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
        autoProgressView?.startProgress()
        onPageChangedListener(currentPosition)
    }

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
            owner: LifecycleOwner?,
            viewPager: ViewPager2?
        ) = CarouselAutoScroll(owner, viewPager)

        fun setupWithViewPager(
            owner: LifecycleOwner?,
            viewPager: ViewPager2?,
            autoProgressView: AutoProgressView
        ) = CarouselAutoScroll(owner, viewPager, autoProgressView)
    }
}