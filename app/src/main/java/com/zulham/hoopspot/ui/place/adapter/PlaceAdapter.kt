package com.zulham.hoopspot.ui.place.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.hoopspot.R
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.databinding.ListPlaceBinding

class PlaceAdapter(private val hoops: List<HoopsEntityItem>): RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(hoops : HoopsEntityItem)
    }

    inner class PlaceViewHolder(private val binding: ListPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        private val w = 1000
        private val h = 1000

        fun bind(hoopsView : HoopsEntityItem){
            Glide.with(binding.root)
                .load(hoopsView.placeImg)
                .error(R.drawable.ic_launcher_foreground)
                .apply(RequestOptions().override(w, h))
                .into(binding.imgPlace)

            binding.tvPlaceName.text = hoopsView.placeName
            binding.tvItemDate.text = hoopsView.placeAddress

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(hoopsView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ListPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(hoops[position])
    }

    override fun getItemCount(): Int = hoops.size
}