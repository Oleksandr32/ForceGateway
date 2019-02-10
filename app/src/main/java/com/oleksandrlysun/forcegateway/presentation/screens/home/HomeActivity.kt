package com.oleksandrlysun.forcegateway.presentation.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extension.bindView
import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeModule
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

	@Inject
	lateinit var mPagerAdapter: HomeFragmentPagerAdapter

	private val mToolbar by bindView<Toolbar>(R.id.toolbar)
	private val mTabLayout by bindView<TabLayout>(R.id.tab_layout)
	private val mTabsViewPager by bindView<ViewPager>(R.id.tabs_view_pager)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injectDependencies()
		setContentView(R.layout.activity_home)
		setupWidgets()
	}

	private fun setupWidgets() {
		setSupportActionBar(mToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)

		mTabsViewPager.adapter = mPagerAdapter
		mTabLayout.setupWithViewPager(mTabsViewPager)
	}

	private fun injectDependencies() {
		val module = HomeModule(this)
		applicationComponent.getHomeComponent(module)
			.inject(this)
	}
}