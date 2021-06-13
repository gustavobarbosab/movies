package io.github.gustavobarbosab.movies.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import io.github.gustavobarbosab.movies.MainActivity
import io.github.gustavobarbosab.movies.MovieApplication

fun Activity.requireAppComponent() = (this.application as MovieApplication).appComponent

fun Fragment.requireMainActivity(): MainActivity = (this.requireActivity() as MainActivity)

fun Fragment.findAppNavController(): NavController = (this.requireActivity() as MainActivity).navController

fun Fragment.requireAppComponent() =
    (this.requireActivity().application as MovieApplication).appComponent

