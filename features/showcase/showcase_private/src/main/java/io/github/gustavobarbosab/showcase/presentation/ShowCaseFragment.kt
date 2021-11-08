package io.github.gustavobarbosab.showcase.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.detail.domain.model.MovieDetail
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.navigateSafely
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.showcase.BuildConfig
import io.github.gustavobarbosab.showcase.R
import io.github.gustavobarbosab.showcase.databinding.FragmentShowCaseBinding
import io.github.gustavobarbosab.showcase.di.DaggerMovieListComponent
import io.github.gustavobarbosab.showcase.di.MovieListComponent
import io.github.gustavobarbosab.showcase.di.ShowCaseInjector
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewState.Action.*
import io.github.gustavobarbosab.showcase.presentation.movielist.MovieListAdapter
import javax.inject.Inject

@ModuleScope
class ShowCaseFragment : BaseFragment<FragmentShowCaseBinding>(), ShowCaseInjector {

    @Inject
    lateinit var viewModelFactory: ShowCaseViewModelFactory

    private lateinit var viewModel: ShowCaseViewModel

    private val latestAdapter = MovieListAdapter(this::onItemClicked)
    private val popularAdapter = MovieListAdapter(this::onItemClicked)
    private val topRatedAdapter = MovieListAdapter(this::onItemClicked)

    override val layoutId: Int = R.layout.fragment_show_case

    override var component: MovieListComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShowCaseViewModel::class.java)
    }

    private fun createComponent() {
        component = DaggerMovieListComponent.factory().create(requireAppComponent())
        component?.inject(this)
    }

    override fun initializeViews(savedInstance: Bundle?) {
        observeViewModel()
        setupToolbar()
        setupMovieList()
        setupVersion()
    }

    private fun setupMovieList() = with(binding) {
        latestMovies.adapter = latestAdapter
        topRated.adapter = topRatedAdapter
        popularMovies.adapter = popularAdapter

        latestMovies.reloadListener(viewModel::getLatestMovies)
        popularMovies.reloadListener(viewModel::getPopularMovies)
        topRated.reloadListener(viewModel::getTopRatedMovies)
    }

    private fun setupVersion() {
        binding.textVersion.text = getString(
            R.string.show_case_app_version,
            BuildConfig.VERSION_NAME
        )
    }

    private fun setupToolbar() {
        applicationToolbar {
            logo(io.github.gustavobarbosab.commons.R.drawable.ic_default_icon)
            shortcutIcon(R.drawable.ic_search)
            shortcutListener(viewModel::onSearchMovie)
        }
    }

    private fun observeViewModel() {
        viewModel.state.action.observe(viewLifecycleOwner, {
            when (it) {
                ShowLatestLoading -> binding.latestMovies.showShimmer()
                ErrorLoadLatest -> binding.latestMovies.showTryAgain()
                ShowPopularLoading -> binding.popularMovies.showShimmer()
                ErrorLoadPopular -> binding.popularMovies.showTryAgain()
                ShowTopRatedLoading -> binding.topRated.showShimmer()
                ErrorLoadTopRated -> binding.topRated.showTryAgain()
                RedirectToSearch -> context?.toast("Pesquisar")
                is ShowMovieDetails -> startDetails(it.movie)
                else -> {
                }
            }
        })

        viewModel.state.latestMovies.observe(viewLifecycleOwner) {
            latestAdapter.movieList = it
            binding.latestMovies.showMovieList()
        }

        viewModel.state.popularMovies.observe(viewLifecycleOwner) {
            popularAdapter.movieList = it
            binding.popularMovies.showMovieList()
        }

        viewModel.state.topRatedMovies.observe(viewLifecycleOwner) {
            topRatedAdapter.movieList = it
            binding.topRated.showMovieList()
        }
    }

    private fun onItemClicked(movie: MovieShowCase) {
        viewModel.showDetails(movie)
    }

    private fun startDetails(movie: MovieDetail) {
        val action = ShowCaseFragmentDirections.actionDetailFragment(movie)
        findAppNavController().navigateSafely(action) {
            requireContext().toast(it.message.toString())
        }
    }
}