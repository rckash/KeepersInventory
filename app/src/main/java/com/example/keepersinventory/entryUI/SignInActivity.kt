package com.example.keepersinventory.entryUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepersinventory.MainActivity
import com.example.keepersinventory.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.btnSignInSignIn.setOnClickListener {
            val myIntent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}