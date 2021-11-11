package io.github.gustavobarbosab.movies.navigation.directions.showcase

import androidx.navigation.NavDirections
import io.github.gustavobarbosab.detail.MovieDetail
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailMapper
import io.github.gustavobarbosab.movies.navigation.directions.DirectionAdapter
import io.github.gustavobarbosab.showcase.presentation.ShowCaseFragmentDirections

class ShowCaseDetailDirection(private val detail: MovieDetail) : DirectionAdapter {

    private var mapper = MovieDetailMapper()

    override fun createDirection(): NavDirections {
        val argument = mapper.map(detail)
        return ShowCaseFragmentDirections.actionDetailFragment(argument)
    }
}