package com.zulham.hoopspot.api

import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object{
        private const val BASE_URL = "https://capstone-api-ptzf4vjc4a-et.a.run.app"

        private val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttp = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        @Volatile
        private var retrofit: Retrofit? = null
        @InternalCoroutinesApi
        fun getInstance(): Retrofit {
            return retrofit ?: kotlinx.coroutines.internal.synchronized(this) {
                retrofit ?: Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp)
                    .build()
            }
        }
    }

}