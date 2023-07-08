package com.example.keepersinventory

import androidx.recyclerview.widget.RecyclerView
import com.example.keepersinventory.databinding.InventoryItemLayoutBinding

class InventoryItemViewHolder(val binding: InventoryItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(inventory: Inventory) {
        binding.tvItemName.text = inventory.item_name
        binding.tvDescription.text = inventory.description
        binding.tvQuantity.text = inventory.quantity.toString()
    }
}