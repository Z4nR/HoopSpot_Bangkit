package com.zulham.hoopspot.ui.parking.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.databinding.ActivityMainBinding
import com.zulham.hoopspot.ui.parking.adapter.ParkingAdapter
import com.zulham.hoopspot.viewmodel.ViewModelFactory
import com.zulham.hoopspot.vo.Resource
import com.zulham.hoopspot.vo.StatusVo

class ParkingActivity : AppCompatActivity() {

    private lateinit var bindingParking : ActivityMainBinding
    private lateinit var parkingAdapter: ParkingAdapter
    private var backPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)

        bindingParking = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingParking.root)

        val factory = ViewModelFactory.getInstance(this)
        val parkingViewModel = ViewModelProvider(this, factory)[ParkingViewModel::class.java]
        parkingViewModel.getParking().observe(this,parkingObserver)

    }
    private val parkingObserver = Observer<Resource<PagedList<ParkingResponse>>> { list->
        if (list != null) {
            when (list.status) {
//                StatusVo.LOADING -> bindingPlace.progressBar.visibility = View.VISIBLE
                StatusVo.SUCCESS -> {
                    parkingAdapter.setParking(list.data)
                    parkingAdapter.notifyDataSetChanged()
//                    fragmentMovie.progressBar.visibility = View.GONE
                }
                StatusVo.ERROR -> {
//                    fragmentMovie.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }
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