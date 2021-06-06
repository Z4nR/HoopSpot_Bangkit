package com.zulham.hoopspot.ui.parking.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.data.remote.response.ParkingResponse

class ParkingDetailViewModel(private val repository: HoopsRepository): ViewModel() {

    private lateinit var detailPark: LiveData<ParkingResponse>

    fun setParkingDetail(place_id: Int, park_id: Int) {
        Log.d("place", place_id.toString())
        Log.d("park", park_id.toString())
        detailPark = repository.getParkDetail(place_id, park_id)
    }

    fun getParkingDetail() : LiveData<ParkingResponse>{
        return detailPark
    }

}