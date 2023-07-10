package com.example.keepersinventory.accountDatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AccountDatabaseHelper(context: Context): SQLiteOpenHelper( context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "accounts.db"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE account(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT,
            password TEXT
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS accounts")
        onCreate(db)
    }

    //CRUD function

    //CREATE
    fun insertData(account: Account) {
        val db = writableDatabase

        val sql = "INSERT INTO account (username, password) VALUES ( ?, ?)"
        val args = arrayOf(account.username, account.password)
        db.execSQL(sql, args)
    }

    //READ
    fun getAllData(): MutableList<Account> {
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM account", null)
        val accountList = mutableListOf<Account>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val username = cursor.getString(1)
            val password = cursor.getString(2)

            var newAccount = Account(id, username, password)
            accountList.add(newAccount)
        }

        cursor.close()
        return accountList
    }

    //UPDATE
    fun updateData(account: Account) {
        val db = writableDatabase
        val updateQuery = "UPDATE account SET username='${account.username}', password='${account.password}' WHERE id = ${account.id}"
        db.execSQL(updateQuery)
    }

    //DELETE
    fun deleteData(id: Int) {
        val db = writableDatabase
        val deleteQuery = "DELETE FROM account WHERE id = $id;"
        db.execSQL(deleteQuery)
    }

}