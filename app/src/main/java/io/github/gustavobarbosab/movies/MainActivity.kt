package io.github.gustavobarbosab.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.setupWithNavController
import io.github.gustavobarbosab.commons.extension.navController
import io.github.gustavobarbosab.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val navController
        get() = supportFragmentManager.navController(R.id.nav_main_fragment)

    private val destinationListener =
        NavController.OnDestinationChangedListener { _, destination, arguments ->
            changeToolbarType(destination, arguments)
            changeBottomNavVisibility(arguments)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbar.setupBackButton(this::onBackPressed)
        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(destinationListener)
    }

    private fun changeToolbarType(navDestination: NavDestination, arguments: Bundle?) {
        when (arguments?.getBoolean(TOOLBAR_LOGO)) {
            true -> binding.toolbar.setLogo()
            else -> toolbarDefault(navDestination.label.toString())
        }
    }

    private fun toolbarDefault(title: String) {
        binding.toolbar.apply {
            setTitle(title)
            showBackButton()
            hideShortcutIcon()
        }
    }

    private fun changeBottomNavVisibility(arguments: Bundle?) {
        binding.bottomNav.isVisible = arguments?.getBoolean(SHOW_NAVBAR) == true
    }

    override fun onDestroy() {
        binding.toolbar.removeListener()
        super.onDestroy()
    }

    companion object {
        const val SHOW_NAVBAR = "SHOW_NAVBAR"
        const val TOOLBAR_LOGO = "TOOLBAR_LOGO"
    }
}
