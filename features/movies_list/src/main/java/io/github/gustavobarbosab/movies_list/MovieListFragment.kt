package io.github.gustavobarbosab.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.github.gustavobarbosab.core.di.scope.ModuleScope
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.movies_list.databinding.FragmentMovieListBinding
import io.github.gustavobarbosab.movies_list.di.DaggerMovieListComponent
import javax.inject.Inject

@ModuleScope
class MovieListFragment : Fragment() {

    @Inject
    lateinit var viewModel: MovieListViewModel

    lateinit var binding: FragmentMovieListBinding

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
        viewModel.getPopularMovies()
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, {
            when (it) {
                MovieListViewModel.MovieListState.HideLoading -> binding.textMovie.setText("Carregando...")
                MovieListViewModel.MovieListState.ShowLoading -> {
                }
            }
        })

        viewModel.movieResponse.observe(viewLifecycleOwner, {
            binding.textMovie.text = it.first().name
        })
    }
}