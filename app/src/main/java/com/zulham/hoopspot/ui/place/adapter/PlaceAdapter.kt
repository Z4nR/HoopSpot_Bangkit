package com.zulham.hoopspot.ui.place.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.databinding.ListPlaceBinding

class PlaceAdapter : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    private var placeData = ArrayList<PlaceResponse>()

    fun setPlace(place: List<PlaceResponse>?) {
        if (place == null) return
        this.placeData.clear()
        this.placeData.addAll(place)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val placeBinding = ListPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(placeBinding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placeData[position]
        holder.bind(place)
    }

    override fun getItemCount(): Int = placeData.size

    inner class PlaceViewHolder(private val binding: ListPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        private val w = 1000
        private val h = 1000

        fun bind(hoopsView : PlaceResponse){
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + hoopsView.photo)
                .error(R.drawable.ic_launcher_foreground)
                .apply(RequestOptions().override(w, h))
                .into(binding.imgPlace)

            binding.tvParkName.text = hoopsView.title
            binding.tvItemDate.text = hoopsView.rilis

//            itemView.setOnClickListener {
//                onItemClickCallback?.onItemClicked(hoopsView)
//            }
        }

    }
}