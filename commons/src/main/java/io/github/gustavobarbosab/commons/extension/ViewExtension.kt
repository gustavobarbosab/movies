package io.github.gustavobarbosab.commons.extension

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.commons.ui.base.BaseParentFragment

fun Fragment.parentNavController() =
    (parentFragment?.parentFragment as BaseParentFragment?)?.fragmentNavController

fun Context.toast(message: String) = Toast.makeText(this,message,Toast.LENGTH_SHORT).show()