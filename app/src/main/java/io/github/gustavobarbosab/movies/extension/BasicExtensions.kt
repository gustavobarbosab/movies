package io.github.gustavobarbosab.movies.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.movies.MovieApplication

fun Activity.requireAppComponent() = (this.application as MovieApplication).appComponent

fun Fragment.requireAppComponent() = (this.requireActivity().application as MovieApplication).appComponent