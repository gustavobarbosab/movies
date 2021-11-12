package io.github.gustavobarbosab.movies.detail.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.commons.widget.progress.MoovieProgressDialogFragment
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.BackButtonType
import io.github.gustavobarbosab.detail.R
import io.github.gustavobarbosab.detail.databinding.FragmentMovieDetailBinding
import io.github.gustavobarbosab.movies.detail.di.DaggerDetailComponent
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ButtonState
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ViewActions
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import javax.inject.Inject

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    override val layoutId: Int = R.layout.fragment_movie_detail

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: MovieDetailViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel

    override fun initializeViews(savedInstance: Bundle?) {
        DaggerDetailComponent
            .factory()
            .create(requireAppComponent())
            .inject(this)

        setupViewModel()
        setupToolbar()
        observeStates()
        binding.movieFab.setOnClickListener { viewModel.favoriteMovie() }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.init(args.detailModel)
    }

    private fun setupToolbar() = applicationToolbar {
        title = "Detalhes"
        backButtonType = BackButtonType.ARROW
    }

    private fun observeStates() = with(viewModel.state) {
        movie.observe(viewLifecycleOwner) { movieDetail ->
            binding.moviePoster.loadImage(movieDetail.poster)
            binding.movieName.text = movieDetail.title
            binding.movieDescription.text = movieDetail.description
        }

        actions.observe(viewLifecycleOwner) {
            when (it) {
                ViewActions.FavoriteMovieFailure -> favoriteMovieFailure()
                ViewActions.FavoriteMovieSuccess -> favoriteMovieSuccess()
                ViewActions.SearchFavoriteMoviesFailure -> searchFavoriteMoviesFailure()
                ViewActions.HideLoading -> hideLoading()
                ViewActions.ShowLoading -> showLoading()
            }
        }

        favoriteButtonState.observe(viewLifecycleOwner) {
            val newImage = when (it) {
                ButtonState.Filled -> io.github.gustavobarbosab.commons.R.drawable.ic_heart_filled
                ButtonState.Outline -> io.github.gustavobarbosab.commons.R.drawable.ic_heart_outline
            }
            binding.movieFab.setImageResource(newImage)
        }
    }

    private fun searchFavoriteMoviesFailure() {

    }

    private fun favoriteMovieSuccess() {
        requireContext().toast("Filme adicionado aos favoritos")
    }

    private fun favoriteMovieFailure() {
        requireContext().toast("Houve uma falha ao favoritar o filme")
    }
}