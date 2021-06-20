package io.github.gustavobarbosab.movies.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import io.github.gustavobarbosab.movies.MainActivity
import io.github.gustavobarbosab.movies.MovieApplication
import java.lang.Exception

fun Activity.requireAppComponent() = (this.application as MovieApplication).appComponent

fun Fragment.requireMainActivity(): MainActivity = (this.requireActivity() as MainActivity)

fun Fragment.findAppNavController() = requireMainActivity().navController

fun NavController.navigateSafely(
    navDirections: NavDirections,
    onError: (Exception) -> Unit = {}
) =
    try {
        navigate(navDirections)
    } catch (e: Exception) {
        onError(e)
    }

fun Fragment.requireAppComponent() =
    (this.requireActivity().application as MovieApplication).appComponent

fun Fragment.toolbar() =
    requireMainActivity()
        .binding
        .toolbar
        .hideBackButton()
        .hideShortcutIcon()



