package io.github.gustavobarbosab.showcase.presentation

import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
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
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewState.Action.*
import javax.inject.Inject

@ModuleScope
class ShowCaseFragment : BaseFragment<FragmentMovieListBinding>() {

    @Inject
    lateinit var viewModelFactory: ShowCaseViewModelFactory

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

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ShowCaseViewModel::class.java)
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
            shortcutListener(viewModel::onSearchMovie)
        }
    }

    private fun observeViewModel() {
        viewModel.states.action.observe(viewLifecycleOwner, {
            when (it) {
                HideBannerLoading -> binding.groupBannerTop.isInvisible = true
                ShowBannerLoading -> binding.groupBannerTop.isInvisible = false
                HideLatestLoading -> binding.nowPlaying.hideShimmer()
                ShowLatestLoading -> binding.nowPlaying.showShimmer()
                HidePopularLoading -> binding.popularMovies.hideShimmer()
                ShowPopularLoading -> binding.popularMovies.showShimmer()
                HideTopRatedLoading -> binding.topRated.hideShimmer()
                ShowTopRatedLoading -> binding.topRated.showShimmer()
                RedirectToSearch -> context?.toast("Pesquisar")
            }
        })

        viewModel.states.bannerMovies.observe(viewLifecycleOwner) {
            bannerTopAdapter.items = it
        }

        viewModel.states.latestMovies.observe(viewLifecycleOwner) {
            binding.nowPlaying.loadMovies(getString(R.string.show_case_playing_now), it)
        }

        viewModel.states.popularMovies.observe(viewLifecycleOwner) {
            binding.popularMovies.loadMovies(getString(R.string.show_case_popular), it)
        }

        viewModel.states.topRatedMovies.observe(viewLifecycleOwner) {
            binding.topRated.loadMovies(getString(R.string.show_case_top_rated), it)
        }
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