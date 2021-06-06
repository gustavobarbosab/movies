package io.github.gustavobarbosab.core.data.config

import io.github.gustavobarbosab.core.BuildConfig

class AppConfigWrapper {

    fun formatImageUrl(endpoint: String?) = BuildConfig.IMAGE_BASE_URL + endpoint

    fun appBaseUrl() = BuildConfig.MOVIE_BASE_URL
}