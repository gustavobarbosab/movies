package io.github.gustavobarbosab.showcase.presentation

import android.os.Bundle
import android.view.View
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.widget.carousel.CarouselAutoScroll
import io.github.gustavobarbosab.commons.widget.carousel.DepthPageTransformer
import io.github.gustavobarbosab.commons.widget.scrollablemovie.MovieScrollableModel
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies.BuildConfig
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.navigateSafely
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.showcase.R
import io.github.gustavobarbosab.showcase.databinding.FragmentMovieListBinding
import io.github.gustavobarbosab.showcase.di.DaggerMovieListComponent
import javax.inject.Inject

@ModuleScope
class ShowCaseFragment : BaseFragment<FragmentMovieListBinding>() {

    @Inject
    lateinit var viewModel: ShowCaseViewModel

    override val layoutId: Int = R.layout.fragment_movie_list

    private val bannerTopAdapter = PagerCarouselAdapter(this::onItemClicked)

    private var carouselAutoScroll: CarouselAutoScroll? = null

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
        binding.nowPlaying.clickListener = this::onItemClicked
        binding.popularMovies.clickListener = this::onItemClicked
        binding.topRated.clickListener = this::onItemClicked
        binding.textVersion.text = "v${BuildConfig.VERSION_NAME}"
    }

    private fun setupBanner() {
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
            shortcutListener { requireContext().toast("Clicooou!") }
        }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, {
            when (it) {
                ShowCaseViewModel.MovieListState.HideLoading -> {
                }
                ShowCaseViewModel.MovieListState.ShowLoading -> {
                }
            }
        })

        viewModel.popularMovieResponse.observe(viewLifecycleOwner, {
            binding.popularMovies.loadMovies("Popular", it)
        })

        viewModel.playingNowResponse.observe(viewLifecycleOwner, {
            bannerTopAdapter.items = it
        })

        viewModel.topRatedResponse.observe(viewLifecycleOwner, {
            binding.topRated.loadMovies("Top Rated", it)
        })

        viewModel.latestMovieResponse.observe(viewLifecycleOwner, {
            binding.nowPlaying.loadMovies("Now Playing", it)
        })
    }

    private fun onItemClicked(movie: MovieScrollableModel) {
        context?.toast(movie.id.toString())
        findAppNavController()
            .navigateSafely(ShowCaseFragmentDirections.actionDetailFragment()) {
                context?.toast("Ops, houve um erro :/")
            }
    }

}