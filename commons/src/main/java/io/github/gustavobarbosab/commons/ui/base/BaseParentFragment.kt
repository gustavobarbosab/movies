package io.github.gustavobarbosab.commons.ui.base

import androidx.navigation.NavController
import io.github.gustavobarbosab.commons.widget.MovieToolbar

abstract class BaseParentFragment : BaseFragment() {
    abstract val fragmentNavController: NavController?
}