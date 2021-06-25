package io.github.gustavobarbosab.core.data.router

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.dynamicfeatures.DynamicInstallMonitor

class FeatureInstallManager(
    context: Context
) {
    val installMonitor = DynamicInstallMonitor()

    fun installFeature(
        feature: FeatureName,
        viewLifecycleOwner: LifecycleOwner,
        onSuccess: () -> Unit,
        onFailure: (e: Exception) -> Unit = {}
    ) {

    }
}