package com.example.keepersinventory.entryUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepersinventory.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}