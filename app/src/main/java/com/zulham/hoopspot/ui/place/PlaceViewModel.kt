package com.zulham.hoopspot.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem

class PlaceViewModel(private val repository: HoopsRepository) : ViewModel() {

    private lateinit var listOnline: LiveData<List<HoopsEntityItem>>

    fun setData(){
        listOnline = repository.getPlaceList()
    }

    fun getData(): LiveData<List<HoopsEntityItem>> {
        return listOnline
    }

}