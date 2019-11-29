package com.vancelopes.chucknorris.api

import com.vancelopes.chucknorris.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ChuckAPI class.
 * This is the main API fetching service.
 */

@Module
object APIModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideEndpoint(retrofit: Retrofit): ChuckAPI {
        return retrofit.create(ChuckAPI::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.URL)
            .build()
    }
}