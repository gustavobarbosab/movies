package io.github.gustavobarbosab.movies.navigation

import androidx.fragment.app.Fragment

interface MovieBaseNavigation<MODEL> {
    fun startNewFlow(fragment: Fragment, model: MODEL, error: (Exception) -> Unit = {})
}