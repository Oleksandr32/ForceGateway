package com.oleksandrlysun.forcegateway.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oleksandrlysun.forcegateway.extension.startActivity
import com.oleksandrlysun.forcegateway.presentation.screens.home.HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<HomeActivity>()
        finish()
    }
}
