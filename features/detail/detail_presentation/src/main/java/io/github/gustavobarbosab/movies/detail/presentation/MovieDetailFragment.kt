package io.github.gustavobarbosab.movies.detail.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.BackButtonType
import io.github.gustavobarbosab.movies.detail.R
import io.github.gustavobarbosab.movies.detail.databinding.FragmentMovieDetailBinding
import io.github.gustavobarbosab.movies.detail.di.DaggerDetailComponent
import io.github.gustavobarbosab.movies.detail.model.DetailsButtonType
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieState.ViewAction
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

        favoriteButtonState.observe(viewLifecycleOwner) {
            val newImage = when (it) {
                DetailsButtonType.Filled -> io.github.gustavobarbosab.commons.R.drawable.ic_heart_filled
                DetailsButtonType.Outline -> io.github.gustavobarbosab.commons.R.drawable.ic_heart_outline
            }
            binding.movieFab.setImageResource(newImage)
        }

        actions.observe(viewLifecycleOwner) {
            when (it) {
                ViewAction.HideLoading -> hideLoading()
                ViewAction.ShowLoading -> showLoading()
                ViewAction.StartScreenFailure -> showSnackBar("Não conseguimos buscar seu histórico de filmes favoritos :(")
                ViewAction.FavoriteMovieFailure -> showSnackBar("Ops... tivemos um problema =(")
                ViewAction.MovieUnliked -> showSnackBar("Filme removido dos favoritos")
                ViewAction.MovieLiked -> showSnackBar("Filme adicionado aos favoritos")
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}