package io.github.gustavobarbosab.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class FeatureDownloadingFragment : AbstractProgressFragment() {

    protected val loading: ProgressBar?
        get() = view?.findViewById(R.id.progress)

    protected val text: TextView?
        get() = view?.findViewById(R.id.text)

    protected val layoutId: Int
        get() = R.layout.fragment_basic_loading

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCancelled() {
        text?.text = "O download foi cancelado!"
    }

    override fun onFailed(errorCode: Int) {
        text?.text = "O download falhou  =X"
    }

    override fun onProgress(
        status: Int,
        bytesDownloaded: Long,
        bytesTotal: Long
    ) {
        when (status) {
            SplitInstallSessionStatus.DOWNLOADING -> text?.text = "Fazendo download..."
            SplitInstallSessionStatus.INSTALLING -> text?.text = "Instalando..."
            else -> {
            }
        }

        loading?.progress = calculateProgress(bytesDownloaded.toInt(), bytesTotal.toInt())
    }

    private fun calculateProgress(bytesDownloaded: Int, bytesTotal: Int): Int = try {
        (bytesDownloaded / bytesTotal) * 100
    } catch (e: Exception) {
        100
    }
}