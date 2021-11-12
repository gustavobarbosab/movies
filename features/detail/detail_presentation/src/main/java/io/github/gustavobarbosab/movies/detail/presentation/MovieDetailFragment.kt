package io.github.gustavobarbosab.movies.detail.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.BackButtonType
import io.github.gustavobarbosab.detail.R
import io.github.gustavobarbosab.detail.databinding.FragmentMovieDetailBinding
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ButtonState
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieViewModelState.ViewActions
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.navigation.MoovieNavigation
import javax.inject.Inject

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    override val layoutId: Int = R.layout.fragment_movie_detail

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var navigation: MoovieNavigation

    @Inject
    lateinit var viewModelFactory: MovieDetailViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel

    override fun initializeViews(savedInstance: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        setupToolbar()
        observeStates()
    }

    private fun setupToolbar() = applicationToolbar {
        title = "Detalhes"
        backButtonType = BackButtonType.ARROW
    }

    private fun observeStates() = with(viewModel.state) {
        movie.observe(viewLifecycleOwner) { movieDetail ->
            binding.moviePoster.loadImage(movieDetail.poster)
            binding.movieName.text = movieDetail.name
            binding.movieDescription.text = movieDetail.description
        }

        actions.observe(viewLifecycleOwner) {
            when (it) {
                ViewActions.FavoriteMovieFailure -> requireContext().toast("Houve uma falha ao favoritar o filme")
                ViewActions.FavoriteMovieSuccess -> requireContext().toast("Filme adicionado aos favoritos")
                ViewActions.HideLoading -> Snackbar.make(binding.root,"Hide Loading", Snackbar.LENGTH_SHORT).show()
                ViewActions.SearchFavoriteMoviesFailure -> searchFavoriteMoviesFailure()
                ViewActions.ShowLoading -> Snackbar.make(binding.root,"Show Loading", Snackbar.LENGTH_SHORT).show()
            }
        }

        favoriteButtonState.observe(viewLifecycleOwner) {
            val newImage = when (it) {
                ButtonState.Filled -> R.drawable.ic_star_filled
                ButtonState.Outline -> R.drawable.ic_star_outline
            }
            binding.movieFab.setImageResource(newImage)
        }
    }

    private fun searchFavoriteMoviesFailure() {

    }
}