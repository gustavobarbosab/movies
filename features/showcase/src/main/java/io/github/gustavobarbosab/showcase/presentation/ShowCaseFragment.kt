package io.github.gustavobarbosab.showcase.presentation

import android.os.Bundle
import androidx.core.view.isInvisible
import com.facebook.shimmer.ShimmerFrameLayout
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.widget.carousel.CarouselAutoScroll
import io.github.gustavobarbosab.commons.widget.carousel.DepthPageTransformer
import io.github.gustavobarbosab.commons.widget.scrollablemovie.MovieScrollableModel
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.navigateSafely
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.showcase.BuildConfig
import io.github.gustavobarbosab.showcase.R
import io.github.gustavobarbosab.showcase.databinding.FragmentMovieListBinding
import io.github.gustavobarbosab.showcase.di.DaggerMovieListComponent
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewModel.MovieListState.*
import java.lang.Exception
import javax.inject.Inject

@ModuleScope
class ShowCaseFragment : BaseFragment<FragmentMovieListBinding>() {

    @Inject
    lateinit var viewModel: ShowCaseViewModel

    override val layoutId: Int = R.layout.fragment_movie_list

    private val bannerTopAdapter = PagerCarouselAdapter(this::onItemClicked)

    private var carouselAutoScroll: CarouselAutoScroll? = null

    /**
     * Unfortunately, I get shimmer instance from "findViewById" method,
     * because dataBinding was not resolve Shimmer's methods.
     */
    private var shimmerTop: ShimmerFrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMovieListComponent
            .factory()
            .create(requireAppComponent())
            .inject(this)
    }

    override fun initializeViews(savedInstance: Bundle?) {
        observeViewModel()
        setupToolbar()
        setupBanner()
        setupListeners()
        setupVersion()
    }

    private fun setupListeners() = with(binding) {
        nowPlaying.clickListener = this@ShowCaseFragment::onItemClicked
        popularMovies.clickListener = this@ShowCaseFragment::onItemClicked
        topRated.clickListener = this@ShowCaseFragment::onItemClicked
    }

    private fun setupVersion() {
        binding.textVersion.text = getString(
            R.string.show_case_app_version,
            BuildConfig.VERSION_NAME
        )
    }

    private fun setupBanner() {
        shimmerTop = view?.findViewById(R.id.shimmer_banner_top)
        carouselAutoScroll =
            CarouselAutoScroll.setupWithViewPager(
                viewLifecycleOwner,
                binding.bannerTop,
                binding.autoProgress
            )
        binding.bannerTop.adapter = bannerTopAdapter
        binding.bannerTop.setPageTransformer(DepthPageTransformer())
    }

    private fun setupToolbar() {
        applicationToolbar {
            logo(io.github.gustavobarbosab.commons.R.drawable.ic_default_icon)
            shortcutIcon(R.drawable.ic_search)
            shortcutListener {
                // TODO call search screen
            }
        }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, {
            when (it) {
                BannerHideLoading -> binding.groupBannerTop.isInvisible = true
                BannerShowLoading -> binding.groupBannerTop.isInvisible = false
                LatestHideLoading -> binding.nowPlaying.hideShimmer()
                LatestShowLoading -> binding.nowPlaying.showShimmer()
                PopularHideLoading -> binding.popularMovies.hideShimmer()
                PopularShowLoading -> binding.popularMovies.showShimmer()
                TopRatedHideLoading -> binding.topRated.hideShimmer()
                TopRatedShowLoading -> binding.topRated.showShimmer()
            }
        })

        viewModel.playingNowResponse.observe(viewLifecycleOwner, { playingNowMovies ->
            bannerTopAdapter.items = playingNowMovies
        })

        viewModel.popularMovieResponse.observe(viewLifecycleOwner, { popularMovies ->
            binding.popularMovies.loadMovies(
                getString(R.string.show_case_popular),
                popularMovies
            )
        })

        viewModel.topRatedResponse.observe(viewLifecycleOwner, { topRatedMovies ->
            binding.topRated.loadMovies(
                getString(R.string.show_case_top_rated),
                topRatedMovies
            )
        })

        viewModel.latestMovieResponse.observe(viewLifecycleOwner, { latestMovies ->
            binding.nowPlaying.loadMovies(
                getString(R.string.show_case_playing_now),
                latestMovies
            )
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