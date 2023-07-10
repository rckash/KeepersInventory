package com.example.keepersinventory.entryUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.keepersinventory.MainActivity
import com.example.keepersinventory.accountDatabase.Account
import com.example.keepersinventory.accountDatabase.AccountDatabaseHelper
import com.example.keepersinventory.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var accountList: MutableList<Account>
    private lateinit var accountDatabaseHelper: AccountDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //databaseHelper Instantiation
        accountDatabaseHelper = AccountDatabaseHelper(this)

        //data declaration
        accountList = getData()

        binding.btnSignInSignIn.setOnClickListener {
            val username = binding.tfUsernameSignIn.editText?.text.toString()
            val password = binding.tfPasswordSignIn.editText?.text.toString()
            checkIfAccountExists(username, password)
        }
        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            val myIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.tvSignUpSignIn.setOnClickListener {
            val myIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
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
    private fun checkIfAccountExists(username: String, password: String) {
        val accountForCheck = Account(0, username, password)
        val myBoolean = accountDatabaseHelper.accountCheck(accountForCheck)
        if (myBoolean == true) {
            val myIntent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        } else {
            Toast.makeText(this@SignInActivity, "Incorrect Credentials", Toast.LENGTH_SHORT).show()
        }
    }

}