package io.github.gustavobarbosab.movies.navigation.directions

import androidx.navigation.NavDirections
import io.github.gustavobarbosab.detail.MovieDetail
import io.github.gustavobarbosab.movies.navigation.mapper.mapToArgument
import io.github.gustavobarbosab.showcase.presentation.ShowCaseFragmentDirections

object ShowCaseDirectionsWrapper {
    fun actionDetailFragment(detail: MovieDetail): NavDirections {
        return ShowCaseFragmentDirections.actionDetailFragment(detail.mapToArgument())
    }
}
