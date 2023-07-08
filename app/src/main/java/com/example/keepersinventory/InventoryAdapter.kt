package com.example.keepersinventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keepersinventory.databinding.InventoryItemLayoutBinding

class InventoryAdapter(private val inventories: List<Inventory>): RecyclerView.Adapter<InventoryItemViewHolder>() {

//    var onDeleteClick: ((Inventory) -> Unit)? = null
//    var onUpdateClick: ((Inventory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InventoryItemLayoutBinding.inflate(inflater, parent, false)
        return InventoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return inventories.size
    }

    override fun onBindViewHolder(holder: InventoryItemViewHolder, position: Int) {
        holder.bind(inventories[position])
//        holder.binding.apply {
//
//        }
    }

}