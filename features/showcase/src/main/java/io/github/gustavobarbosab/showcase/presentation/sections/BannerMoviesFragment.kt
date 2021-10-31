package io.github.gustavobarbosab.showcase.presentation.sections

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.widget.carousel.CarouselAutoScroll
import io.github.gustavobarbosab.commons.widget.carousel.DepthPageTransformer
import io.github.gustavobarbosab.commons.widget.scrollablemovie.MovieScrollableModel
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.navigateSafely
import io.github.gustavobarbosab.showcase.R
import io.github.gustavobarbosab.showcase.databinding.FragmentBannerMoviesBinding
import io.github.gustavobarbosab.showcase.presentation.PagerCarouselAdapter
import io.github.gustavobarbosab.showcase.presentation.ShowCaseFragmentDirections
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewModel

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
        binding.bannerTop.adapter = bannerTopAdapter
        binding.bannerTop.setPageTransformer(DepthPageTransformer())
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.states.bannerMovies.observe(viewLifecycleOwner) {
            bannerTopAdapter.items = it
        }
        viewModel.states.action.observe(viewLifecycleOwner, {
            // TODO
        })
    }

    private fun onItemClicked(movie: MovieScrollableModel) {
        findAppNavController().navigateSafely(
            navDirections = ShowCaseFragmentDirections.actionDetailFragment(),
            onError = this::onLoadDetailsFail
        )
        context?.toast(movie.id.toString())
    }

    private fun onLoadDetailsFail(exception: Exception) {
        context?.toast(getString(R.string.show_case_generic_error))
    }
}