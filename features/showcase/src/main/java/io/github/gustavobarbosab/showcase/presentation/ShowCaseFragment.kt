package io.github.gustavobarbosab.showcase.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
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
import io.github.gustavobarbosab.showcase.di.MovieListComponent
import io.github.gustavobarbosab.showcase.di.ShowCaseInjector
import io.github.gustavobarbosab.showcase.presentation.ShowCaseViewState.Action.*
import javax.inject.Inject

@ModuleScope
class ShowCaseFragment : BaseFragment<FragmentMovieListBinding>(), ShowCaseInjector {

    @Inject
    lateinit var viewModelFactory: ShowCaseViewModelFactory

    private lateinit var viewModel: ShowCaseViewModel

    override val layoutId: Int = R.layout.fragment_movie_list

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
        setupListeners()
        setupVersion()
    }

    private fun setupListeners() = with(binding) {
        latestMovies.clickListener = this@ShowCaseFragment::onItemClicked
        latestMovies.reloadListener(viewModel::getLatestMovies)
        popularMovies.clickListener = this@ShowCaseFragment::onItemClicked
        popularMovies.reloadListener(viewModel::getPopularMovies)
        topRated.clickListener = this@ShowCaseFragment::onItemClicked
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
                else -> {
                }
            }
        })

        viewModel.state.latestMovies.observe(viewLifecycleOwner) {
            binding.latestMovies.loadMovies(getString(R.string.show_case_playing_now), it)
        }

        viewModel.state.popularMovies.observe(viewLifecycleOwner) {
            binding.popularMovies.loadMovies(getString(R.string.show_case_popular), it)
        }

        viewModel.state.topRatedMovies.observe(viewLifecycleOwner) {
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