package io.github.gustavobarbosab.showcase.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.commons.extension.toolbar
import io.github.gustavobarbosab.core.data.router.FeatureInstallManager
import io.github.gustavobarbosab.core.data.router.FeatureName
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.core.domain.model.Movie
import io.github.gustavobarbosab.home.HomeFragmentDirections
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.showcase.R
import io.github.gustavobarbosab.showcase.databinding.FragmentMovieListBinding
import io.github.gustavobarbosab.showcase.di.DaggerMovieListComponent
import javax.inject.Inject

@ModuleScope
class ShowCaseFragment : Fragment() {

    @Inject
    lateinit var viewModel: ShowCaseViewModel

    lateinit var binding: FragmentMovieListBinding

    private val adapter = MovieListAdapter(this::onItemClicked)

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
        toolbar()?.setLogo()
        viewModel.getPopularMovies()
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
            adapter.movies = it
            binding.movies.adapter = adapter
        })
    }

    private fun onItemClicked(movie: Movie) {
        FeatureInstallManager(requireContext())
            .installFeature(FeatureName.MovieDetail, {
                Toast.makeText(context, "Instalooou!", Toast.LENGTH_SHORT).show()
                goToDetail()
            }, {
                Toast.makeText(context, "Falhooou!", Toast.LENGTH_SHORT).show()
            })
    }

    private fun goToDetail() {
        findAppNavController().navigate(HomeFragmentDirections.actionDetailFragment())
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShowCaseFragment()
    }
}