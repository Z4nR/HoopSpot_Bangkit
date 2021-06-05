package com.zulham.hoopspot.ui.parking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.ParkingItem
import com.zulham.hoopspot.databinding.ListParkingBinding

class ParkingAdapter(private val hoops: List<ParkingItem>): RecyclerView.Adapter<ParkingAdapter.PlaceViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(hoops : ParkingItem)
    }

    inner class PlaceViewHolder(private val binding: ListParkingBinding) : RecyclerView.ViewHolder(binding.root) {
        private val w = 1000
        private val h = 1000

        fun bind(hoopsView : ParkingItem){
            Glide.with(binding.root)
                .load(hoopsView.parkImg)
                .error(R.drawable.ic_launcher_foreground)
                .apply(RequestOptions().override(w, h))
                .into(binding.imgPlace)

            binding.tvParkName.text = hoopsView.parkName
            binding.tvItemDate.text = hoopsView.parkAddress

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(hoopsView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ListParkingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        hoops.get(position).let { holder.bind(it) }
    }

    override fun getItemCount(): Int = hoops.size
}