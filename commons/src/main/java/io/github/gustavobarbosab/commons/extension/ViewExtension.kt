package io.github.gustavobarbosab.commons.extension

import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.commons.ui.base.BaseParentFragment

fun Fragment.parentNavController() =
    (parentFragment?.parentFragment as BaseParentFragment?)?.fragmentNavController