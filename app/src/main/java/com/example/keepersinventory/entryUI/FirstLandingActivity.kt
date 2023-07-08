package com.example.keepersinventory.entryUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepersinventory.databinding.ActivityFirstLandingBinding

class FirstLandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            val myIntent = Intent(this@FirstLandingActivity, SignUpActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}