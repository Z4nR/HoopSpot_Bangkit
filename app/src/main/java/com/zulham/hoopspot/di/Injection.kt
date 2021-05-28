package com.zulham.hoopspot.di

import android.content.Context
import com.zulham.hoopspot.api.ApiConfig
import com.zulham.hoopspot.data.database.HoopsDatabase
import com.zulham.hoopspot.data.local.LocalDataSource
import com.zulham.hoopspot.data.source.HoopsRepository
import com.zulham.hoopspot.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): HoopsRepository{
        val database = HoopsDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.hoopsDao())
        val appExecutors = AppExecutors()
        val apiConfig = ApiConfig.getInstance()
        return HoopsRepository.getInstance(apiConfig, localDataSource, appExecutors)
    }
}