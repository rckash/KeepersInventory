package com.example.keepersinventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keepersinventory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InventoryAdapter
    private lateinit var inventoryList: MutableList<Inventory>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recyclerview setup
        recyclerView = binding.rvMain
        recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerview test
        val inventoryList = mutableListOf(
            Inventory( "Wood","An item from tree used for building infastructures and crafts", 2.0f),
            Inventory( "Tiles","An item from tree used for flooring", 12.0f),
            Inventory( "Medical Pills","An item from tree used for medication", 3.0f)
        )

        //adapter initialization
        adapter = InventoryAdapter(inventoryList)

        recyclerView.adapter = adapter
    }
}