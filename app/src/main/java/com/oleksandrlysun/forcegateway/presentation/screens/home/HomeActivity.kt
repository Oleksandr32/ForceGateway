package com.oleksandrlysun.forcegateway.presentation.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extensions.bindView
import com.oleksandrlysun.forcegateway.extensions.setOnPageChangeListener
import com.oleksandrlysun.forcegateway.extensions.setOnTabSelectedListener
import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeComponent
import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeModule
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

	@Inject
	lateinit var mPagerAdapter: HomeTabsFragmentPagerAdapter

	internal lateinit var component: HomeComponent

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
		mTabsViewPager.run {
			adapter = mPagerAdapter
			setOnPageChangeListener { position ->
				mTabLayout.getTabAt(position)?.select()
			}
		}
		mTabLayout.setOnTabSelectedListener { tab ->
			mTabsViewPager.currentItem = tab.position
		}
	}

	private fun injectDependencies() {
		val module = HomeModule(this)
		component = applicationComponent.getHomeComponent(module)
		component.inject(this)
	}
}