package io.github.gustavobarbosab.core.di.modules

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.BuildConfig
import io.github.gustavobarbosab.core.data.config.AppConfigWrapper
import io.github.gustavobarbosab.core.data.network.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideAppWrapper() = AppConfigWrapper()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        appConfigWrapper: AppConfigWrapper
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(appConfigWrapper.appBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        @Named(BuildConfig.BUILD_TYPE) loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    @Singleton
    @Named("debug")
    fun provideLoggingInterceptorDebug(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @Named("release")
    fun provideLoggingInterceptorRelease(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
}