package io.github.gustavobarbosab.movies.featuredownload

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent

class FeatureLoadingState {
    val action: MutableLiveData<FeatureLoadingAction> = SingleLiveEvent()

    sealed class FeatureLoadingAction {
        object DownloadStarted : FeatureLoadingAction()
        object DownloadCanceled : FeatureLoadingAction()
        object DownloadFailed : FeatureLoadingAction()
        class UpdateDownloadProgress(val progress: Int) : FeatureLoadingAction()
    }
}