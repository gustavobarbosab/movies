package io.github.gustavobarbosab.movies

import android.app.Application
import io.github.gustavobarbosab.movies.di.AppComponent
import io.github.gustavobarbosab.movies.di.DaggerAppComponent

class MovieApplication : Application() {

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