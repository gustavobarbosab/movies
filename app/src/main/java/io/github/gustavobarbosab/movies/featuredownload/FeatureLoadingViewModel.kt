package io.github.gustavobarbosab.movies.featuredownload

import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.movies.featuredownload.FeatureLoadingState.FeatureLoadingAction

class FeatureLoadingViewModel : BaseViewModel<FeatureLoadingState>() {

    override val state: FeatureLoadingState = FeatureLoadingState()

    fun downloadCancelled() {
        state.action.value = FeatureLoadingAction.DownloadCanceled
    }

    fun downloadFailed() {
        state.action.value = FeatureLoadingAction.DownloadFailed
    }

    fun progressChanged(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        calculateProgress(bytesDownloaded, bytesTotal)
        updateState(status)
    }

    private fun updateState(status: Int) {
        state.action.value = when (status) {
            SplitInstallSessionStatus.DOWNLOADING,
            SplitInstallSessionStatus.INSTALLING -> FeatureLoadingAction.DownloadStarted
            SplitInstallSessionStatus.FAILED -> FeatureLoadingAction.DownloadFailed
            else -> null
        } ?: return
    }

    private fun calculateProgress(bytesDownloaded: Long, bytesTotal: Long) {
        val progress = try {
            (bytesDownloaded.toInt() / bytesTotal.toInt()) * ONE_HUNDRED_PERCENT
        } catch (e: Exception) {
            ONE_HUNDRED_PERCENT
        }
        state.action.value = FeatureLoadingAction.UpdateDownloadProgress(progress)
    }

    companion object {
        private const val ONE_HUNDRED_PERCENT = 100
    }
}