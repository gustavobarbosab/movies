package io.github.gustavobarbosab.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import io.github.gustavobarbosab.commons.extension.navController
import io.github.gustavobarbosab.commons.ui.base.BaseParentFragment
import io.github.gustavobarbosab.commons.widget.MovieToolbar
import io.github.gustavobarbosab.home.databinding.FragmentHomeBinding

class HomeFragment : BaseParentFragment() {

    private var binding: FragmentHomeBinding? = null

    override val toolbar: MovieToolbar?
        get() = binding?.toolbar

    override val fragmentNavController: NavController
        get() = childFragmentManager.navController(R.id.nav_home_fragment)

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
        binding?.bottomNav?.setupWithNavController(fragmentNavController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}