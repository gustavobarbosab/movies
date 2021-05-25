package io.github.gustavobarbosab.home

import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.core.router.HomeRouter

class HomeRouterImpl : HomeRouter {
    override fun retrieveFragment(): Fragment = HomeFragment()
}