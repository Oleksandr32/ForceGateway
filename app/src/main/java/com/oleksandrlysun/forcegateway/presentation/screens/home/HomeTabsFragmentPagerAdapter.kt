package com.oleksandrlysun.forcegateway.presentation.screens.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeTabsFragmentPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

	val fragments = mutableListOf<Fragment>()

	override fun getItem(position: Int): Fragment {
		return fragments[position]
	}

	override fun getCount(): Int {
		return fragments.size
	}
}