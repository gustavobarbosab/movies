package io.github.gustavobarbosab.favorite.presentation

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.favorite.di.DaggerFavoritesComponent
import io.github.gustavobarbosab.favorite.model.FavoriteModel
import io.github.gustavobarbosab.favorite.presentation.FavoritesMoviesState.LayoutState
import io.github.gustavobarbosab.favorite.presentation.FavoritesMoviesState.ViewAction
import io.github.gustavobarbosab.movies.favorite.databinding.FragmentFavoriteMoviesBinding
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.movies.favorite.R
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>() {

    @Inject
    lateinit var viewModelFactory: FavoritesMoviesViewModelFactory
    private lateinit var viewModel: FavoritesMoviesViewModel
    private val adapter = FavoritesMoviesRecyclerAdapter(this::unlikeMovie)

    override val layoutId: Int = R.layout.fragment_favorite_movies

    override fun initializeViews(savedInstance: Bundle?) {
        DaggerFavoritesComponent.factory()
            .create(requireAppComponent())
            .inject(this)

        createViewModel()
        setupToolbar()
        observeState()
        binding.favoriteMoviesRecyclerView.adapter = adapter
        binding.favoritesTryAgain.buttonListener { viewModel.fetchFavorites() }
        viewModel.fetchFavorites()
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(FavoritesMoviesViewModel::class.java)
    }

    private fun setupToolbar() = applicationToolbar {
        title = getString(R.string.favorites_toolbar_title)
    }

    private fun unlikeMovie(position: Int, favoriteModel: FavoriteModel) {
        viewModel.unlikeMovie(favoriteModel, position)
    }

    private fun observeState() = with(viewModel.state) {
        movies.observe(viewLifecycleOwner) {
            adapter.list = it
        }

        layout.observe(viewLifecycleOwner) {
            when (it) {
                LayoutState.HideAll -> hideAllViews()
                LayoutState.ShowEmptyState -> showEmptyState()
                LayoutState.ShowRecyclerView -> showRecyclerView()
                LayoutState.ShowTryAgain -> showTryAgain()
            }
        }

        actions.observe(viewLifecycleOwner) {
            when (it) {
                ViewAction.HideLoading -> hideLoading()
                ViewAction.ShowLoading -> showLoading()
                ViewAction.MovieUnliked -> movieUnlikedSuccess()
                ViewAction.UnlikeMovieFailure -> unlikeMovieFailure()
                is ViewAction.RemoveUnlikedMovie -> adapter.removeMovie(it.position)
            }
        }
    }

    private fun movieUnlikedSuccess() {
        Snackbar.make(binding.root, "Filme removido =D", Snackbar.LENGTH_SHORT).show()
    }

    private fun unlikeMovieFailure() {
        requireContext().toast("Falha ao remover filme dos favoritos")
    }

    private fun hideAllViews() {
        binding.favoritesTryAgain.isGone = true
        binding.favoriteMoviesRecyclerView.isGone = true
        binding.favoritesEmptyState.isGone = true
    }

    private fun showTryAgain() {
        binding.favoritesTryAgain.isVisible = true
        binding.favoriteMoviesRecyclerView.isGone = true
        binding.favoritesEmptyState.isGone = true
    }

    private fun showEmptyState() {
        binding.favoritesEmptyState.isVisible = true
        binding.favoritesTryAgain.isGone = true
        binding.favoriteMoviesRecyclerView.isGone = true
    }

    private fun showRecyclerView() {
        binding.favoriteMoviesRecyclerView.isVisible = true
        binding.favoritesEmptyState.isGone = true
        binding.favoritesTryAgain.isGone = true
    }
}