package com.zulham.hoopspot.di

import android.content.Context
import com.zulham.hoopspot.api.ApiConfig
import com.zulham.hoopspot.api.ApiService
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.data.remote.RemoteDataSource
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
object Injection {
    fun provideRepository(context: Context): HoopsRepository {
        val retrofit = ApiConfig.getInstance().create(ApiService::class.java)
        val remoteDataSource = RemoteDataSource.getInstance(retrofit)

        return HoopsRepository.getInstance(remoteDataSource)
    }
}