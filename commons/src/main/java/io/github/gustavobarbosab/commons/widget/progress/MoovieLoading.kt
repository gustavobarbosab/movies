package io.github.gustavobarbosab.commons.widget.progress

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class MoovieLoading {

    fun showLoading(fragmentManager: FragmentManager) {
        val dialogProgress = getFragmentProgress(fragmentManager)?.dialog
        if (dialogProgress?.isShowing == true)
            return

        MoovieProgressDialogFragment().show(fragmentManager, TAG)
    }

    fun hideLoading(fragmentManager: FragmentManager) {
        val fragmentProgress = getFragmentProgress(fragmentManager)
        fragmentProgress?.let {
            it.dialog?.cancel()
            it.dismissAllowingStateLoss()
        }
    }

    private fun getFragmentProgress(fragmentManager: FragmentManager) =
        fragmentManager.findFragmentByTag(TAG) as? DialogFragment?

    companion object {
        private const val TAG = "TAG_LOADING"
    }
}