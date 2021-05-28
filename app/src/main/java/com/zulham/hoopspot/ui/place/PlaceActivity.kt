package com.zulham.hoopspot.ui.place

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.databinding.ActivityMainBinding
import com.zulham.hoopspot.ui.place.adapter.PlaceAdapter
import com.zulham.hoopspot.viewmodel.ViewModelFactory
import com.zulham.hoopspot.vo.Resource
import com.zulham.hoopspot.vo.StatusVo

class PlaceActivity : AppCompatActivity() {

    private lateinit var bindingPlace : ActivityMainBinding
    private lateinit var placeAdapter: PlaceAdapter
    private var backPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingPlace = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingPlace.root)

        val factory = ViewModelFactory.getInstance(this)
        val placeViewModel = ViewModelProvider(this, factory)[PlaceViewModel::class.java]
        placeViewModel.getPlace().observe(this,placeObserver)


        with(bindingPlace.rvPlace) {
            placeAdapter = PlaceAdapter()
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = placeAdapter
        }

    }

    private val placeObserver = Observer<Resource<PagedList<PlaceResponse>>> { list->
        if (list != null) {
            when (list.status) {
//                StatusVo.LOADING -> bindingPlace.progressBar.visibility = View.VISIBLE
                StatusVo.SUCCESS -> {
                    placeAdapter.setPlace(list.data)
                    placeAdapter.notifyDataSetChanged()
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