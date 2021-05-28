package com.zulham.hoopspot.ui.parking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.databinding.ListParkingBinding

class ParkingAdapter : RecyclerView.Adapter<ParkingAdapter.PlaceViewHolder>() {
    private var parkingData = ArrayList<ParkingResponse>()

    fun setParking(parking : List<ParkingResponse>?){
        if(parking == null) return
        this.parkingData.clear()
        this.parkingData.addAll(parking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ListParkingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val parking  = parkingData[position]
        holder.bind(parking)
    }

    override fun getItemCount(): Int = parkingData.size

    inner class PlaceViewHolder(private val binding: ListParkingBinding) : RecyclerView.ViewHolder(binding.root) {
        private val w = 1000
        private val h = 1000

        fun bind(hoopsView : ParkingResponse){
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + hoopsView.photo)
                .error(R.drawable.ic_launcher_foreground)
                .apply(RequestOptions().override(w, h))
                .into(binding.imgPlace)

            binding.tvParkName.text = hoopsView.title
            binding.tvItemDate.text = hoopsView.tanggal

//            itemView.setOnClickListener {
//                onItemClickCallback?.onItemClicked(hoopsView)
//            }
        }

    }


}