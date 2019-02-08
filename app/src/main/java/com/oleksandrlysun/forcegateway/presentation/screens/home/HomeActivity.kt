package com.oleksandrlysun.forcegateway.presentation.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extension.bindView

class HomeActivity : AppCompatActivity() {

	private val toolbar by bindView<Toolbar>(R.id.toolbar)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
		setupWidgets()
	}

	private fun setupWidgets() {
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)
	}
}