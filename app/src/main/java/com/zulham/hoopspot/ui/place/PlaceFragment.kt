package com.zulham.hoopspot.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.databinding.FragmentPlaceBinding
import com.zulham.hoopspot.ui.adapter.PlaceAdapter
import com.zulham.hoopspot.ui.parking.main.ParkingActivity
import com.zulham.hoopspot.ui.parking.main.ParkingActivity.Companion.EXTRA_DATA
import com.zulham.hoopspot.utils.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PlaceFragment : Fragment() {

    private var _binding : FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)

        val factory = ViewModelFactory.getInstance(requireContext())
        val placeViewModel = ViewModelProvider(this, factory)[PlaceViewModel::class.java]

        placeViewModel.setData()
        placeViewModel.getData().observe(viewLifecycleOwner, {
            showLoading(false)
            recyclerV(it)
        })

    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressBarPlace.visibility = View.VISIBLE
        } else {
            binding.progressBarPlace.visibility = View.GONE
        }
    }

    private fun recyclerV(place: List<HoopsEntityItem>) {
        binding.rvPlace.apply {
            val placeAdapter = PlaceAdapter(place)

            adapter = placeAdapter

            placeAdapter.setOnItemClickCallback(object : PlaceAdapter.OnItemClickCallback{
                override fun onItemClicked(hoops: HoopsEntityItem) {
                    val intent = Intent(context, ParkingActivity::class.java)
                    intent.putExtra(EXTRA_DATA, hoops)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }



}