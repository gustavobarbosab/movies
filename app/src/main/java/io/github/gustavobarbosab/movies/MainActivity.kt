package io.github.gustavobarbosab.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import io.github.gustavobarbosab.commons.extension.navController
import io.github.gustavobarbosab.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val navController
        get() = supportFragmentManager.navController(R.id.nav_main_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbar.setupBackButton(this::onBackPressed)
        setupToolbar()
    }

    private fun setupToolbar() {
        navController.addOnDestinationChangedListener { _: NavController,
                                                        navDestination: NavDestination,
                                                        _: Bundle? ->
            when (navDestination.id) {
                R.id.fragment_home -> toolbarHome()
                else -> toolbarDefault(navDestination.label.toString())
            }
        }
    }

    private fun toolbarHome() {
        binding.toolbar.setLogo()
    }

    private fun toolbarDefault(title: String) {
        binding.toolbar.apply {
            setTitle(title)
            showBackButton()
        }
    }

    override fun onDestroy() {
        binding.toolbar.removeListener()
        super.onDestroy()
    }

    override fun onBackPressed() {
        // TODO remover logica
        if (!navController.popBackStack()) {
            finish()
        }
    }
}
