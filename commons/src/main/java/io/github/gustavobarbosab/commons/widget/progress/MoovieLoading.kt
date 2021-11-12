package io.github.gustavobarbosab.commons.widget.progress

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MoovieLoading(private var lifecycleOwner: LifecycleOwner?) {

    private var lastProgress: MoovieProgressDialogFragment? = null

    init {
        lifecycleOwner?.lifecycle?.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    hideLoading()
                    lifecycleOwner = null
                }
            }
        })
    }

    fun showLoading(fragmentManager: FragmentManager) {
        if (isProgressShowing()) {
            return
        }

        lastProgress = MoovieProgressDialogFragment()
        lastProgress?.show(fragmentManager, TAG)
    }

    private fun isProgressShowing(): Boolean =
        lastProgress?.isVisible == true

    fun hideLoading() {
        lastProgress?.dismissAllowingStateLoss()
        lastProgress = null
    }

    companion object {
        private const val TAG = "TAG"
    }
}