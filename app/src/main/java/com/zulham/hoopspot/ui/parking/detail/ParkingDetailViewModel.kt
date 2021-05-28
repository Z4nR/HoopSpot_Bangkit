package com.zulham.hoopspot.ui.parking.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.source.HoopsRepository
import com.zulham.hoopspot.vo.Resource

class ParkingDetailViewModel (private val repository: HoopsRepository) : ViewModel() {
    private var parkingId = MutableLiveData<String>()

    fun setParkingId(parkingId: String) {
        this.parkingId.value = parkingId
    }

    var getListParking: LiveData<Resource<ParkingResponse>> = Transformations.switchMap(parkingId) { mParkingId->
        repository.getDetailParking(mParkingId)
    }
}