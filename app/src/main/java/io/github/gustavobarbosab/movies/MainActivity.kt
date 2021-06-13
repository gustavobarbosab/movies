package io.github.gustavobarbosab.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.gustavobarbosab.commons.extension.navController
import io.github.gustavobarbosab.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val navController
        get() = supportFragmentManager.navController(R.id.nav_main_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onBackPressed() {
        // TODO remover logica
        if (!navController.popBackStack()) {
            finish()
        }
    }
}
