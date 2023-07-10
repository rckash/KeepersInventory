package com.example.keepersinventory.entryUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.keepersinventory.accountDatabase.Account
import com.example.keepersinventory.accountDatabase.AccountDatabaseHelper
import com.example.keepersinventory.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var accountList: MutableList<Account>
    private lateinit var accountDatabaseHelper: AccountDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //databaseHelper Instantiation
        accountDatabaseHelper = AccountDatabaseHelper(this)

        //data declaration
        accountList = getData()

        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            val myIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.tvSignInSignUp.setOnClickListener {
            val myIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(myIntent)
            finish()
        }


        binding.btnSignUpSignUp.setOnClickListener {
            val username = binding.tfUsernameSignUp.editText?.text.toString()
            val password = binding.tfPasswordSignUp.editText?.text.toString()

            var newAccount = Account(0, username, password)
            //add new data to database table
            addData(newAccount)
            //add new note to list
            accountList.add(newAccount)
            Log.d("SIGNUP", "Registered: $newAccount")

            //redirection to sign in activity
            val myIntent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(myIntent)
            finish()
        }


    }
    private fun getData(): MutableList<Account> {
        return accountDatabaseHelper.getAllData()
    }
    private fun addData(account: Account) {
        accountDatabaseHelper.insertData(account)
        Toast.makeText(this, "Account Registered Successfully", Toast.LENGTH_SHORT).show()
    }
}