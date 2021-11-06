package io.github.gustavobarbosab.showcase.presentation.banner

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.widget.carousel.CarouselAutoScroll
import io.github.gustavobarbosab.commons.widget.carousel.DepthPageTransformer
import io.github.gustavobarbosab.commons.widget.scrollablemovie.MovieScrollableModel
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.navigateSafely
import io.github.gustavobarbosab.showcase.R
import io.github.gustavobarbosab.showcase.databinding.FragmentBannerMoviesBinding
import io.github.gustavobarbosab.showcase.presentation.ShowCaseFragmentDirections
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewModel
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewState

class BannerMoviesFragment : BaseFragment<FragmentBannerMoviesBinding>() {

    private lateinit var viewModel: ShowCaseViewModel

    private val bannerTopAdapter = PagerCarouselAdapter(this::onItemClicked)

    override val layoutId: Int = R.layout.fragment_banner_movies

    private var carouselAutoScroll: CarouselAutoScroll? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment()).get(ShowCaseViewModel::class.java)
    }

    override fun initializeViews(savedInstance: Bundle?) {
        carouselAutoScroll = CarouselAutoScroll.setupWithViewPager(
            viewLifecycleOwner,
            binding.bannerTop,
            binding.autoProgress
        )
        binding.apply {
            bannerTop.adapter = bannerTopAdapter
            bannerTop.setPageTransformer(DepthPageTransformer())
            bannerTryAgain.buttonListener(viewModel::getBannerMovies)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.bannerMovies.observe(viewLifecycleOwner) {
            tryAgainVisibility(visible = false)
            bannerTopAdapter.items = it
        }
        viewModel.state.action.observe(viewLifecycleOwner, {
            if (it != ShowCaseViewState.Action.ErrorLoadBanner)
                return@observe

            tryAgainVisibility(visible = true)
        })
    }

    private fun tryAgainVisibility(visible: Boolean) = with(binding) {
        bannerGroup.isGone = visible
        bannerTryAgain.isVisible = visible
    }

    private fun onItemClicked(movie: MovieScrollableModel) {
        viewModel.showDetails(movie)
    }

    private fun startDetails(movie: Movie) {
        val action = ShowCaseFragmentDirections.actionDetailFragment(movie)
        findAppNavController().navigateSafely(action) {
            requireContext().toast(it.message.toString())
        }
    }
}