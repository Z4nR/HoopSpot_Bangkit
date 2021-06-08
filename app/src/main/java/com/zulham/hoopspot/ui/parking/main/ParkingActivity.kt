package com.zulham.hoopspot.ui.parking.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.data.remote.response.ParkingItem
import com.zulham.hoopspot.databinding.ActivityParkingBinding
import com.zulham.hoopspot.ui.parking.adapter.ParkingAdapter
import com.zulham.hoopspot.ui.parking.detail.ParkingDetailActivity
import com.zulham.hoopspot.ui.parking.detail.ParkingDetailActivity.Companion.EXTRA_ID
import com.zulham.hoopspot.ui.parking.detail.ParkingDetailActivity.Companion.EXTRA_PARKING
import com.zulham.hoopspot.utils.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ParkingActivity : AppCompatActivity() {

    private lateinit var detailPlace : HoopsEntityItem

    private lateinit var bindingParking : ActivityParkingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)
        bindingParking = ActivityParkingBinding.inflate(layoutInflater)
        setContentView(bindingParking.root)

        detailPlace = intent.getParcelableExtra<HoopsEntityItem>(EXTRA_DATA)!!

        backHome()

        val factory = ViewModelFactory.getInstance(this)
        val parkingViewModel = ViewModelProvider(this, factory)[ParkingViewModel::class.java]

        detailPlace.let{ parkingViewModel.setPlaceDetail(it.placeId!!) }
        parkingViewModel.getParkList().observe(this, {
            recyclerV(it[0].parking!!)
        })

    }

    private fun backHome() {
        supportActionBar?.title = "Parkir List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun recyclerV(park: List<ParkingItem>) {
        bindingParking.rvParking.apply {
            val parkAdapter = ParkingAdapter(park)

            adapter = parkAdapter

            parkAdapter.setOnItemClickCallback(object : ParkingAdapter.OnItemClickCallback{
                override fun onItemClicked(hoops: ParkingItem) {
                    val intent = Intent(context, ParkingDetailActivity::class.java)
                    intent.putExtra(EXTRA_PARKING, hoops.parkId)
                    intent.putExtra(EXTRA_ID, detailPlace.placeId)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

}