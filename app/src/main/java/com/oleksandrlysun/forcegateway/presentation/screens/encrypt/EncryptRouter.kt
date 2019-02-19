package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerFragment

class EncryptRouter(private val activity: AppCompatActivity) {

	fun navigateToFilesPicker() {
		navigateToFragment(FilesPickerFragment(), addToBackStack = false)
	}

	fun finish() {
		activity.finish()
	}

	private fun navigateToFragment(fragment: Fragment, addToBackStack: Boolean = true) {
		activity.supportFragmentManager.beginTransaction().run {
			if (addToBackStack) {
				addToBackStack(null)
			}
			replace(R.id.fragmentContainer, fragment)
			commit()
		}
	}
}