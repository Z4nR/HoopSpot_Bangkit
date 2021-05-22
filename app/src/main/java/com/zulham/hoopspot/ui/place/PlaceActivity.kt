package com.zulham.hoopspot.ui.place

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.HoopsEntity
import com.zulham.hoopspot.databinding.ActivityMainBinding
import com.zulham.hoopspot.ui.parking.main.ParkingActivity
import com.zulham.hoopspot.ui.parking.main.ParkingActivity.Companion.PLACE
import com.zulham.hoopspot.ui.place.adapter.PlaceAdapter

class PlaceActivity : AppCompatActivity() {

    private lateinit var bindingPlace : ActivityMainBinding

    private lateinit var placeViewModel: PlaceViewModel

    private var backPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindingPlace = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingPlace.root)

        placeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PlaceViewModel::class.java)

        placeViewModel.setData()

        placeViewModel.getData().observe(this, {
            recyclerV(it)
        })

    }

    private fun recyclerV(hoops: ArrayList<HoopsEntity>) {
        bindingPlace.rvPlace.apply {
            val placeAdapter =  PlaceAdapter(hoops)
            adapter = placeAdapter

            placeAdapter.setOnItemClickCallback(object : PlaceAdapter.OnItemClickCallback{
                override fun onItemClicked(hoops: HoopsEntity) {
                    val intent = Intent(context, ParkingActivity::class.java)
                    intent.putExtra(PLACE, hoops)
                    startActivity(intent)
                }

            })

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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