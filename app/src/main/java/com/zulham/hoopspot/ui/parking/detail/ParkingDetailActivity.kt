package com.zulham.hoopspot.ui.parking.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.databinding.ActivityParkingDetailBinding
import com.zulham.hoopspot.viewmodel.ViewModelFactory
import com.zulham.hoopspot.vo.StatusVo

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

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[ParkingDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val parkingId = extras.getParcelable<ParkingResponse>(EXTRA_PARKING)
            if (parkingId != null) {
                viewModel.setParkingId(parkingId.id)

                viewModel.getListParking.observe(this, {parkingById->
                    if (parkingById !=null){
                        when(parkingById.status){
//                            StatusVo.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            StatusVo.SUCCESS -> if (parkingById.data != null){
//                                binding.progressBar.visibility = View.GONE
                                getDetailParking(parkingById.data)
                            }
                            StatusVo.ERROR ->{
//                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext,  "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }
    private fun getDetailParking(parking : ParkingResponse){
        binding.apply {
//            judul.text = movie.title
//            tanggal.text = movie.tanggal
//            deksripsi.text = movie.description
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+parking.photo)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
//                .into(binding.imagePoster)
    }
}