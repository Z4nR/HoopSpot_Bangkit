package com.zulham.hoopspot.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.ParkingArray
import com.zulham.hoopspot.databinding.ListParkABinding

class ParkingLayoutAdapter(private val parkirList: List<ParkingArray>):
    RecyclerView.Adapter<ParkingLayoutAdapter.ParkirViewHolder>() {

    inner class ParkirViewHolder(private val binding: ListParkABinding) : RecyclerView.ViewHolder(binding.root){
        private val w = 150
        private val h = 150

        fun bind(parkirView : ParkingArray){
            val position = if (parkirView.valueArray!!) when(parkirView.position){
                "DIAG" -> R.drawable.fill_car_c
                "DOWN" -> R.drawable.fill_car_b
                else -> R.drawable.fill_car_a
            } else R.drawable.outline_car

            Glide.with(binding.root)
                .load(position)
                .apply(RequestOptions().override(w, h))
                .into(binding.imageListLeft)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkirViewHolder {
        val binding = ListParkABinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParkirViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParkirViewHolder, position: Int) {
        parkirList[position].let {holder.bind(it)}
    }

    override fun getItemCount(): Int = parkirList.size

}