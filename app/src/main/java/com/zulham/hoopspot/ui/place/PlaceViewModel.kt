package com.zulham.hoopspot.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.HoopsEntity
import com.zulham.hoopspot.utils.DataDummy

class PlaceViewModel : ViewModel() {

    private val listPlace = MutableLiveData<ArrayList<HoopsEntity>>()

    fun setData(){
        listPlace.postValue(DataDummy.generateDummyPlace())
    }

    fun getData(): LiveData<ArrayList<HoopsEntity>> {
        return listPlace
    }

}