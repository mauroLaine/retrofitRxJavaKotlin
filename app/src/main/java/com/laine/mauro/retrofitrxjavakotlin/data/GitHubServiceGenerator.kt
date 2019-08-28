package com.laine.mauro.retrofitrxjavakotlin.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitHubServiceGenerator {

    companion object {
        private val BASE_URL = "https://api.github.com/"

        private val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        private val httpLoginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

        private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient.build())

        private lateinit var retrofit: Retrofit

        fun <S> createService(serviceClass: Class<S>): S {
            if (!okHttpClient.interceptors().contains(httpLoginInterceptor)) {
                httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BASIC
                okHttpClient.addInterceptor(httpLoginInterceptor)
                retrofitBuilder.client(okHttpClient.build())
                retrofit = retrofitBuilder.build()
            }
            return retrofit.create(serviceClass)
        }
    }
}