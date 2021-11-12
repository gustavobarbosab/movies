package io.github.gustavobarbosab.commons.widget.progress

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MoovieLoading {

    fun showLoading(fragmentManager: FragmentManager) {
        hideLoading(fragmentManager)
        MoovieProgressDialogFragment().show(fragmentManager, TAG)
    }

    fun hideLoading(fragmentManager: FragmentManager) {
        fragmentManager.findFragmentByTag(TAG)
            ?.let { (it as DialogFragment).dismissAllowingStateLoss() }
    }

    companion object {
        private const val TAG = "TAG_LOADING"
    }
}