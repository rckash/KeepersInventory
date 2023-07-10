package com.example.keepersinventory.inventoryDatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper( context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "inventories.db"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE inventory(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            item_name TEXT,
            description TEXT,
            quantity INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS inventories")
        onCreate(db)
    }

    //CRUD function

    //CREATE
    fun insertData(inventory: Inventory) {
        val db = writableDatabase

        val sql = "INSERT INTO inventory (item_name, description, quantity) VALUES ( ?, ?, ?)"
        val args = arrayOf(inventory.item_name, inventory.description, inventory.quantity)
        db.execSQL(sql, args)
    }

    //READ
    fun getAllData(): MutableList<Inventory> {
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM inventory", null)
        val inventoryList = mutableListOf<Inventory>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val item_name = cursor.getString(1)
            val description = cursor.getString(2)
            val quantity = cursor.getInt(3)

            var newInventory = Inventory(id, item_name, description, quantity)
            inventoryList.add(newInventory)
        }

        cursor.close()
        return inventoryList
    }

    //UPDATE
    fun updateData(inventory: Inventory) {
        val db = writableDatabase
        val updateQuery = "UPDATE inventory SET item_name='${inventory.item_name}', description='${inventory.description}', quantity='${inventory.quantity}' WHERE id = ${inventory.id}"
        db.execSQL(updateQuery)
    }

    //DELETE
    fun deleteData(id: Int) {
        val db = writableDatabase
        val deleteQuery = "DELETE FROM inventory WHERE id = $id;"
        db.execSQL(deleteQuery)
    }

}