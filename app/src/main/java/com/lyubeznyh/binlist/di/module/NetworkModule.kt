package com.lyubeznyh.binlist.di.module

import com.lyubeznyh.binlist.BuildConfig
import com.lyubeznyh.binlist.data.network.BinApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideBinApi(client: OkHttpClient):BinApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create()
    }

    private companion object{
        const val BASE_URL = BuildConfig.BASE_URL
        const val TIMEOUT = 60000L
    }
}
