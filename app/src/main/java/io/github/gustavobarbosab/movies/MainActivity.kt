package io.github.gustavobarbosab.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import io.github.gustavobarbosab.core.router.HomeRouter
import io.github.gustavobarbosab.movies.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeRouter: HomeRouter

    lateinit var binding: ActivityMainBinding

    var navController = lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_main_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bottomNav.setupWithNavController(navController.value)
    }

    override fun onSupportNavigateUp() =
        navController.value.navigateUp()
}
