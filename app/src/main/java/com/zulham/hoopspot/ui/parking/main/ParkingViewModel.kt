package com.zulham.hoopspot.ui.parking.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.data.remote.response.ParkingItem

class ParkingViewModel(private val repository: HoopsRepository) : ViewModel() {

    private lateinit var detailPlace: LiveData<List<ParkingItem>>

    fun setPlaceDetail(place_id : Int){
        detailPlace = repository.getParkList(place_id)
    }

    fun getParkList(): LiveData<List<ParkingItem>> {
        Log.d("testing", detailPlace.value.toString())
        return detailPlace
    }

}