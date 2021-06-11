package io.github.gustavobarbosab.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import io.github.gustavobarbosab.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private var navController = lazy {
        (childFragmentManager
            .findFragmentById(R.id.nav_home_fragment) as NavHostFragment)
            .navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding?.bottomNav?.setupWithNavController(navController.value)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}