package com.zulham.hoopspot.ui.parking.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.HoopsEntity
import com.zulham.hoopspot.data.ParkingArea
import com.zulham.hoopspot.databinding.ActivityParkingBinding
import com.zulham.hoopspot.ui.parking.adapter.ParkingAdapter
import com.zulham.hoopspot.ui.parking.detail.ParkingDetailActivity

class ParkingActivity : AppCompatActivity() {

    private lateinit var bindingParking : ActivityParkingBinding

    private lateinit var parkingViewModel: ParkingViewModel

    companion object{
        const val PLACE = "Place"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)

        bindingParking = ActivityParkingBinding.inflate(layoutInflater)
        setContentView(bindingParking.root)

        val place = intent.getParcelableExtra<HoopsEntity>(PLACE)

        parkingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ParkingViewModel::class.java)

        place?.let { parkingViewModel.setPlaceDetail(it) }

        place?.let { parkingViewModel.setParkList(it.placeParkArea) }

        parkingViewModel.getDetail().observe(this, {
            recyclerV(it)
        })

    }

    private fun recyclerV(hoops: List<ParkingArea>?) {
        bindingParking.rvParking.apply {
            val parkingAdapter =  ParkingAdapter(hoops)
            adapter = parkingAdapter

            parkingAdapter.setOnItemClickCallback(object : ParkingAdapter.OnItemClickCallback{
                override fun onItemClicked(hoops: ParkingArea) {
                    val intent = Intent(context, ParkingDetailActivity::class.java)
                    intent.putExtra("Place", hoops.parkId)
                    startActivity(intent)
                }

            })

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}