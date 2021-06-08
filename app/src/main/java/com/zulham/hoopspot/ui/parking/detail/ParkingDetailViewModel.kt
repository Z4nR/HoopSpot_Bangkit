package com.zulham.hoopspot.ui.parking.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.data.remote.response.ParkingArray
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import org.json.JSONObject

class ParkingDetailViewModel(private val repository: HoopsRepository): ViewModel() {

    private lateinit var detailPark: LiveData<ParkingResponse>
    private val avaibleSpot: MutableLiveData<Int> = MutableLiveData()
    private val parkingList: MutableLiveData<List<ParkingArray>> = MutableLiveData()

    private val db = Firebase.database
    private val myReff = db.reference

    fun setParkingDetail(place_id: Int, park_id: Int) {
        detailPark = repository.getParkDetail(place_id, park_id)
    }

    fun getParkingDetail() : LiveData<ParkingResponse>{
        return detailPark
    }

    fun getAvailableSpot() : MutableLiveData<Int>{
        return avaibleSpot
    }

    fun getParkList() : MutableLiveData<List<ParkingArray>>{
        return  parkingList
    }

    fun getParkArray(id : Int){
        val parking = myReff.child("parking/parking_$id")
        val layoutReff = parking.child("layout")
        layoutReff.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val ls = arrayListOf<ParkingArray>()
                val list = snapshot.getValue<List<String>>()
                if (list != null) {
                    for (item in list){
                        val jsonObj = JSONObject(item)
                        ls.add(ParkingArray(jsonObj["id"] as Int, jsonObj["value"] as Boolean))
                    }
                }
                parkingList.postValue(ls)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        val avaibleSpotReff = parking.child("spot_available")
        avaibleSpotReff.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                avaibleSpot.postValue(snapshot.getValue<Int>())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        val parkingListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value
                Log.d("data",  data.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        parking.addValueEventListener(parkingListener)
    }

}