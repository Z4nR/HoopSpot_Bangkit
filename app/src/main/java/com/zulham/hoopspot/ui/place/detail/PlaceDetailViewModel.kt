package com.zulham.hoopspot.ui.place.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.data.source.HoopsRepository
import com.zulham.hoopspot.vo.Resource

class PlaceDetailViewModel(private val repository: HoopsRepository) : ViewModel() {
    private var placeId = MutableLiveData<String>()

    fun setPlaceId(placeId: String) {
        this.placeId.value = placeId
    }

    var getListPlace: LiveData<Resource<PlaceResponse>> = Transformations.switchMap(placeId) { mPlaceId->
        repository.getDetailPlace(mPlaceId)
    }
}