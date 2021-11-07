package io.github.gustavobarbosab.movies.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.detail.R
import io.github.gustavobarbosab.detail.databinding.FragmentMovieDetailBinding
import io.github.gustavobarbosab.movies.extension.applicationToolbar

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    override val layoutId: Int = R.layout.fragment_movie_detail

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun initializeViews(savedInstance: Bundle?) {
        applicationToolbar {
            title("Details")
            backButton(true)
        }
        requireContext().toast(args.detailModel.name)
    }
}