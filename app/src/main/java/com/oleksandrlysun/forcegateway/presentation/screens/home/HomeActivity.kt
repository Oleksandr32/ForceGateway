package com.oleksandrlysun.forcegateway.presentation.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extension.bindView
import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeModule
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

	@Inject
	lateinit var mPagerAdapter: HomeFragmentPagerAdapter

	private val mToolbar by bindView<Toolbar>(R.id.toolbar)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injectDependencies()
		setContentView(R.layout.activity_home)
		setupWidgets()
	}

	private fun setupWidgets() {
		setSupportActionBar(mToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)
	}

	private fun injectDependencies() {
		val module = HomeModule(this)
		applicationComponent.getHomeComponent(module)
			.inject(this)
	}
}