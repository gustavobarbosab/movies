package io.github.gustavobarbosab.commons.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import io.github.gustavobarbosab.commons.R

class BaseLoadingFragment : AbstractProgressFragment() {

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

        loading?.progress = calculateProgress(bytesDownloaded, bytesTotal)
    }

    private fun calculateProgress(bytesDownloaded: Long, bytesTotal: Long): Int =
        (bytesDownloaded / bytesTotal).toInt() * 100
}