package com.example.keepersinventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.SyncStateContract.Helpers.update
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keepersinventory.databinding.ActivityMainBinding
import com.example.keepersinventory.databinding.DialogLayoutBinding
import java.nio.file.Files.delete

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InventoryAdapter
    private lateinit var inventoryList: MutableList<Inventory>
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recyclerview setup
        recyclerView = binding.rvMain
        recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerview test
//        inventoryList = mutableListOf(
//            Inventory( 0, "Wood","An item from tree used for building infrastructures and crafts", 2.0f),
//            Inventory( 0, "Tiles","An item from tree used for flooring", 12.0f),
//            Inventory( 0, "Medical Pills","An item from tree used for medication", 3.0f)
//        )

        //databaseHelper Instantiation
        databaseHelper = DatabaseHelper(this)

        //data declaration
        inventoryList = getData()

        //adapter initialization
        adapter = InventoryAdapter(inventoryList)
        recyclerView.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            showAddDialog()
        }

        adapter.onUpdateClick = { inventory ->
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Edit Note")
            alertDialogBuilder.setMessage("Edit this note?")

            val dialogLayout = layoutInflater.inflate(R.layout.dialog_layout, null)
            val dialogBinding = DialogLayoutBinding.bind(dialogLayout)
            alertDialogBuilder.setView(dialogLayout)

            dialogBinding.tfItemNameDialog.editText?.setText(inventory.item_name)
            dialogBinding.tfDescriptionDialog.editText?.setText(inventory.description)
            dialogBinding.tfQuantityDialog.editText?.setText(inventory.quantity.toString())

            alertDialogBuilder.setPositiveButton("Done") { dialog, _ ->
                val item_name = dialogBinding.tfItemNameDialog.editText?.text.toString()
                val description = dialogBinding.tfDescriptionDialog.editText?.text.toString()
                val quantity = dialogBinding.tfQuantityDialog.editText?.text.toString().toFloat()

                val newItem = Inventory(inventory.id, item_name, description, quantity)
                updateData(newItem)

                //viewholder index finder
                val updateNotePosition = inventoryList.indexOfFirst { it.id == inventory.id }
                if (updateNotePosition != -1) {
                    inventoryList[updateNotePosition] = newItem
                    adapter.notifyItemChanged(updateNotePosition)
                }
            }
            alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog: AlertDialog = alertDialogBuilder.create()
            alertDialog.show()

            Toast.makeText(applicationContext, "${inventory.id}", Toast.LENGTH_SHORT)
        }

        adapter.onDeleteClick = { inventory ->
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Delete Note")
            alertDialogBuilder.setMessage("This item will be gone forever?")

            alertDialogBuilder.setPositiveButton("Delete") { dialog, _ ->
                //delete from database
                deleteData(inventory.id)
                //delete from list
                inventoryList.remove(inventory)
                adapter.notifyDataSetChanged()
            }
            alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog: AlertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }





    }

    private fun showAddDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Add New Item")

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_layout, null)
        val dialogBinding = DialogLayoutBinding.bind(dialogLayout)
        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setPositiveButton("Done") { dialog, _ ->
            val item_name = dialogBinding.tfItemNameDialog.editText?.text.toString()
            val description = dialogBinding.tfDescriptionDialog.editText?.text.toString()
            val quantity = dialogBinding.tfQuantityDialog.editText?.text.toString().toFloat()

            var newItem = Inventory(0, item_name, description, quantity)
            //add new data to database table
            addData(newItem)
            //add new note to list
            inventoryList.add(newItem)
            //notify adapter that data has changed
            recyclerView.adapter?.notifyDataSetChanged()
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun getData(): MutableList<Inventory> {
        return databaseHelper.getAllData()
    }

    private fun addData(inventory: Inventory) {
        databaseHelper.insertData(inventory)
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
    }

    private fun updateData(inventory: Inventory) {
        databaseHelper.updateData(inventory)
        getData()
        Toast.makeText(this, "Inventory Updated", Toast.LENGTH_SHORT).show()
    }

    private fun deleteData(id: Int) {
        databaseHelper.deleteData(id)
        getData()
        Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()
    }
}