package io.github.gustavobarbosab.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.home.databinding.FragmentHomeBinding
import io.github.gustavobarbosab.movies.extension.requireMainActivity

class HomeFragment : Fragment() {

    var binding: FragmentHomeBinding? = null

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
        requireMainActivity().toolbar.setLogo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}