package io.github.gustavobarbosab.showcase.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.widget.carousel.CarouselAutoScroll
import io.github.gustavobarbosab.commons.widget.carousel.DepthPageTransformer
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.home.HomeFragmentDirections
import io.github.gustavobarbosab.movies.BuildConfig
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.movies.extension.toolbar
import io.github.gustavobarbosab.showcase.R
import io.github.gustavobarbosab.showcase.databinding.FragmentMovieListBinding
import io.github.gustavobarbosab.showcase.di.DaggerMovieListComponent
import io.github.gustavobarbosab.showcase.domain.model.MovieShowCase
import javax.inject.Inject

@ModuleScope
class ShowCaseFragment : Fragment() {

    @Inject
    lateinit var viewModel: ShowCaseViewModel

    lateinit var binding: FragmentMovieListBinding

    private val bannerTopAdapter = PagerCarouselAdapter({})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMovieListComponent
            .factory()
            .create(requireAppComponent())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupToolbar()
        setupBanner()
        binding.textVersion.text = "v${BuildConfig.VERSION_NAME}"
        viewModel.getPopularMovies()
    }

    private fun setupBanner() {
        binding.bannerTop.adapter = bannerTopAdapter
        binding.bannerTop.setPageTransformer(DepthPageTransformer())
        CarouselAutoScroll.setupWithViewPager(binding.bannerTop, viewLifecycleOwner)
    }

    private fun setupToolbar() {
        toolbar()
            .setLogo()
            .setShortcutIcon(R.drawable.ic_search)
            .setShortcutListener {
                requireContext().toast("Clicooou!")
            }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, {
            when (it) {
                ShowCaseViewModel.MovieListState.HideLoading -> Toast.makeText(
                    requireContext(),
                    "Hide Loading...",
                    Toast.LENGTH_SHORT
                ).show()
                ShowCaseViewModel.MovieListState.ShowLoading -> Toast.makeText(
                    requireContext(),
                    "Show Loading...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.movieResponse.observe(viewLifecycleOwner, {
            // TODO separar futuramente
            bannerTopAdapter.items = it
            binding.nowPlaying.loadMovies("Now Playing", it)
            binding.topRated.loadMovies("Top Rated", it)
            binding.popularMovies.loadMovies("Popular", it)
        })
    }

    private fun onItemClicked(movie: MovieShowCase) {
        findAppNavController().navigate(HomeFragmentDirections.actionDetailFragment())
    }
}