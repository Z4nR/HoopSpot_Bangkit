package com.zulham.hoopspot.ui.parking.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.data.remote.response.ParkingItem

class ParkingDetailViewModel(private val repository: HoopsRepository): ViewModel() {

    private lateinit var detailPark: LiveData<ParkingItem>

    fun setParkingDetail(park_id: Int) {
        detailPark = repository.getParkDetail(park_id)
    }

    fun getParkingDetail() : LiveData<ParkingItem>{
        return detailPark
    }

}