package io.github.gustavobarbosab.core.data.router

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

class FeatureInstallManager(
    context: Context
) {
    private val splitInstallManager = SplitInstallManagerFactory.create(context)

    fun installFeature(
        feature: FeatureName,
        onSuccess: () -> Unit,
        onFailure: (e: Exception) -> Unit = {}
    ) {
        if (splitInstallManager.installedModules.contains(feature.moduleName)) {
            onSuccess()
            return
        }

        val request = SplitInstallRequest
            .newBuilder()
            .addModule(feature.moduleName)
            .build()

        splitInstallManager.startInstall(request)
            .addOnCompleteListener { onSuccess() }
            .addOnFailureListener(onFailure)
    }
}