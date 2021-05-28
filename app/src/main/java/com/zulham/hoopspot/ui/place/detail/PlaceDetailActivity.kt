package com.zulham.hoopspot.ui.place.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.databinding.ActivityPlaceDetailBinding
import com.zulham.hoopspot.viewmodel.ViewModelFactory
import com.zulham.hoopspot.vo.StatusVo

class PlaceDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PlaceDetailViewModel

    companion object {
        const val EXTRA_PLACE = "extra_places"
    }

    private lateinit var binding : ActivityPlaceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[PlaceDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val placeId = extras.getParcelable<PlaceResponse>(EXTRA_PLACE)
            if (placeId != null) {
                viewModel.setPlaceId(placeId.id)

                viewModel.getListPlace.observe(this, {placeById->
                    if (placeById !=null){
                        when(placeById.status){
//                            StatusVo.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            StatusVo.SUCCESS -> if (placeById.data != null){
//                                binding.progressBar.visibility = View.GONE
                                getDetailPlace(placeById.data)
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
    private fun getDetailPlace(place : PlaceResponse){
        binding.apply {
//            judul.text = movie.title
//            tanggal.text = movie.tanggal
//            deksripsi.text = movie.description
        }

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+place.photo)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
//                .into(binding.imagePoster)
    }
}