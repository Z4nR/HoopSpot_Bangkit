package com.zulham.hoopspot.ui.parking.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zulham.hoopspot.databinding.ActivityParkingDetailBinding
import com.zulham.hoopspot.utils.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ParkingDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ParkingDetailViewModel

    companion object {
        const val EXTRA_PARKING = "extra_parking"
    }

    private lateinit var binding: ActivityParkingDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parkDetail = intent.getIntExtra(EXTRA_PARKING, 0)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[ParkingDetailViewModel::class.java]

        viewModel.setParkingDetail(parkDetail)
        viewModel.getParkingDetail().observe(this, {
            it
        })

    }
}