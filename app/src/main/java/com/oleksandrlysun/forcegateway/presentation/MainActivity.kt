package com.oleksandrlysun.forcegateway.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oleksandrlysun.forcegateway.presentation.screens.home.HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
