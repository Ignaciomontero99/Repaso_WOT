package com.ignmonlop.repasowot.mainModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ignmonlop.repasowot.R
import com.ignmonlop.repasowot.api.Zone
import com.ignmonlop.repasowot.databinding.ItemZoneBinding

class ZonesAdapter(): ListAdapter<Zone, RecyclerView.ViewHolder>(ZoneDiffCallback()) {
    private lateinit var mContext: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemZoneBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_zone, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val zone = getItem(position)

        with(holder as ViewHolder){
            binding.zonaNombre.text = zone.name
        }
    }
    class ZoneDiffCallback: DiffUtil.ItemCallback<Zone>(){
        override fun areItemsTheSame(oldItem: Zone, newItem: Zone): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Zone, newItem: Zone): Boolean {
            return oldItem == newItem
        }
    }
}