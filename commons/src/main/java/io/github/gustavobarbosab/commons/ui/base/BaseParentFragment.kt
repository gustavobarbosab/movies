package io.github.gustavobarbosab.commons.ui.base

import io.github.gustavobarbosab.commons.widget.MovieToolbar

abstract class BaseParentFragment : BaseFragment() {
    abstract val toolbar: MovieToolbar?
}