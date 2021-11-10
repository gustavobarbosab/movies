package io.github.gustavobarbosab.movies.navigation.detail

import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.detail.MovieDetail
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.navigateSafely
import io.github.gustavobarbosab.movies.navigation.MovieBaseNavigation
import io.github.gustavobarbosab.showcase.presentation.ShowCaseFragmentDirections

class DetailsNavigation : MovieBaseNavigation<MovieDetail> {

    private val detailMapper = DetailModelMapper()

    override fun startNewFlow(
        fragment: Fragment,
        model: MovieDetail,
        error: (Exception) -> Unit
    ) = with(fragment) {
        val argument = detailMapper.map(model)
        val action = ShowCaseFragmentDirections.actionDetailFragment(argument)
        findAppNavController().navigateSafely(action,error)
    }
}