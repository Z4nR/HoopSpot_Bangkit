package com.zulham.hoopspot.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulham.hoopspot.data.source.HoopsRepository
import com.zulham.hoopspot.di.Injection
import com.zulham.hoopspot.ui.parking.main.ParkingViewModel
import com.zulham.hoopspot.ui.place.PlaceViewModel

class ViewModelFactory private constructor(private val repository: HoopsRepository) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context)=
            instance?: synchronized(this){
                instance?: ViewModelFactory(Injection.provideRepository(context))
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
        }
        return super.create(modelClass)
    }
}