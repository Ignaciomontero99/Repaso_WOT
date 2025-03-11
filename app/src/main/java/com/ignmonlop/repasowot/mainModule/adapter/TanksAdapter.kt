package com.ignmonlop.repasowot.mainModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ignmonlop.repasowot.R
import com.ignmonlop.repasowot.api.Tank
import com.ignmonlop.repasowot.databinding.ItemTankBinding

class TanksAdapter(): ListAdapter<Tank, RecyclerView.ViewHolder>(TankDiffCallback()) {
    private lateinit var mContext: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemTankBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // Con que vista vamos a trabajar.

        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_tank, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Montas todo
        val tank = getItem(position)

        with(holder as ViewHolder){
            binding.nombreTank.text = tank.model
            binding.pesoTank.text = tank.weight.toString()

            //imagen
            Glide.with(mContext)
                .load(tank.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgTank)
        }
    }

    class TankDiffCallback: DiffUtil.ItemCallback<Tank>(){
        // Comprueba si son iguales o diferente el contenido lo actualiza en la vista automaticamente
        override fun areItemsTheSame(oldItem: Tank, newItem: Tank): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tank, newItem: Tank): Boolean {
            return oldItem == newItem
        }

    }

}