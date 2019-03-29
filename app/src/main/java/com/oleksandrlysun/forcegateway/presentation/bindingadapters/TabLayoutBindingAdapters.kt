package com.oleksandrlysun.forcegateway.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.tabs.TabLayout
import com.oleksandrlysun.forcegateway.extensions.setOnTabSelectedListener

object TabLayoutBindingAdapters {

	@JvmStatic
	@BindingAdapter("titles")
	fun setTitles(tabLayout: TabLayout, titles: List<String>) {
		titles.forEach { title ->
			val tab = tabLayout.newTab().apply { text = title }
			tabLayout.addTab(tab)
		}
	}

	@JvmStatic
	@BindingAdapter("selectedTabPosition")
	fun setSelectedTabPosition(tabLayout: TabLayout, newPosition: Int) {
		if (tabLayout.selectedTabPosition != newPosition) {
			tabLayout.getTabAt(newPosition)?.select()
		}
	}

	@JvmStatic
	@InverseBindingAdapter(attribute = "selectedTabPosition")
	fun getSelectedTabPosition(tabLayout: TabLayout): Int {
		return tabLayout.selectedTabPosition
	}

	@JvmStatic
	@BindingAdapter("app:selectedTabPositionAttrChanged")
	fun setListeners(tabLayout: TabLayout, attrChange: InverseBindingListener) {
		tabLayout.setOnTabSelectedListener { attrChange.onChange() }
	}
}