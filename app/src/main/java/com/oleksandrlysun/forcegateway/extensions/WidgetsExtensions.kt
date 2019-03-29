package com.oleksandrlysun.forcegateway.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.oleksandrlysun.forcegateway.utils.lazyUnsynchronized

fun TabLayout.setOnTabSelectedListener(
		onTabSelected: (TabLayout.Tab) -> Unit = {},
		onTabReselected: (TabLayout.Tab) -> Unit = {},
		onTabUnselected: (TabLayout.Tab) -> Unit = {}
): TabLayout.OnTabSelectedListener {
	val listener = object : TabLayout.OnTabSelectedListener {
		override fun onTabReselected(tab: TabLayout.Tab) {
			onTabReselected(tab)
		}

		override fun onTabUnselected(tab: TabLayout.Tab) {
			onTabUnselected(tab)
		}

		override fun onTabSelected(tab: TabLayout.Tab) {
			onTabSelected(tab)
		}
	}
	addOnTabSelectedListener(listener)
	return listener
}

fun ViewPager.setOnPageChangeListener(
		onPageSelected: (Int) -> Unit = {},
		onPageScrollStateChanged: (Int) -> Unit = {},
		onPageScrolled: (Int, Float, Int) -> Unit = { _, _, _ -> }
): ViewPager.OnPageChangeListener {
	val listener = object : ViewPager.OnPageChangeListener {
		override fun onPageScrollStateChanged(state: Int) {
			onPageScrollStateChanged(state)
		}

		override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
			onPageScrolled(position, positionOffset, positionOffsetPixels)
		}

		override fun onPageSelected(position: Int) {
			onPageSelected(position)
		}
	}
	addOnPageChangeListener(listener)
	return listener
}

fun <ViewT : View> RecyclerView.ViewHolder.bindView(@IdRes idRes: Int): Lazy<ViewT> {
	return lazyUnsynchronized {
		itemView.findViewById<ViewT>(idRes)
	}
}