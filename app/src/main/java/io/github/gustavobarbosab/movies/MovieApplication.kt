package io.github.gustavobarbosab.movies

import com.google.android.play.core.splitcompat.SplitCompatApplication
import io.github.gustavobarbosab.movies.di.AppComponent
import io.github.gustavobarbosab.movies.di.DaggerAppComponent

class MovieApplication : SplitCompatApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }
}