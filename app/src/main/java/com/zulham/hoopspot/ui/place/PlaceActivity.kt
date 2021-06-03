package com.zulham.hoopspot.ui.place

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.databinding.ActivityMainBinding
import com.zulham.hoopspot.ui.parking.main.ParkingActivity
import com.zulham.hoopspot.ui.parking.main.ParkingActivity.Companion.EXTRA_ID
import com.zulham.hoopspot.ui.place.adapter.PlaceAdapter
import com.zulham.hoopspot.utils.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PlaceActivity : AppCompatActivity() {

    private lateinit var bindingPlace : ActivityMainBinding
    private var backPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPlace = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingPlace.root)

        val factory = ViewModelFactory.getInstance(this)
        val placeViewModel = ViewModelProvider(this, factory)[PlaceViewModel::class.java]

        placeViewModel.setData()
        placeViewModel.getData().observe(this, {
            recyclerV(it)
        })

    }

    private fun recyclerV(place: List<HoopsEntityItem>) {
        bindingPlace.rvPlace.apply {
            val placeAdapter = PlaceAdapter(place)

            adapter = placeAdapter

            placeAdapter.setOnItemClickCallback(object : PlaceAdapter.OnItemClickCallback{
                override fun onItemClicked(hoops: HoopsEntityItem) {
                    val intent = Intent(context, ParkingActivity::class.java)
                    intent.putExtra(EXTRA_ID, hoops.placeId)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            Toast.makeText(baseContext, "Press again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }

}