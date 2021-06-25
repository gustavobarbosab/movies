package io.github.gustavobarbosab.core.data.router

sealed class FeatureName(
   val moduleName: String
) {
    object MovieDetail: FeatureName("detail")
}
