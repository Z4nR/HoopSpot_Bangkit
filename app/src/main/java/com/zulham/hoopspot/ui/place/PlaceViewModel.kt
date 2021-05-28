package com.zulham.hoopspot.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.data.source.HoopsRepository
import com.zulham.hoopspot.vo.Resource

//class PlaceViewModel : ViewModel() {
//
//    private val listPlace = MutableLiveData<ArrayList<HoopsEntity>>()
//
//    fun setData(){
//        listPlace.postValue(DataDummy.generateDummyPlace())
//    }
//
//    fun getData(): LiveData<ArrayList<HoopsEntity>> {
//        return listPlace
//    }
//
//}
class PlaceViewModel(private val repository: HoopsRepository) : ViewModel(){
    fun getPlace() : LiveData<Resource<PagedList<PlaceResponse>>> = repository.getPlace()
}