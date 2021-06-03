package com.zulham.hoopspot.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.di.Injection
import com.zulham.hoopspot.ui.parking.detail.ParkingDetailViewModel
import com.zulham.hoopspot.ui.parking.main.ParkingViewModel
import com.zulham.hoopspot.ui.place.PlaceViewModel
import kotlinx.coroutines.InternalCoroutinesApi

class ViewModelFactory private constructor(private val repository: HoopsRepository) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context)=
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ParkingViewModel::class.java) -> {
                return ParkingViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PlaceViewModel::class.java) -> {
                return PlaceViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ParkingDetailViewModel::class.java) -> {
                return ParkingDetailViewModel(repository) as T
            }
        }
        return super.create(modelClass)
    }
}