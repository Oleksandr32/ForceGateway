package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import androidx.fragment.app.Fragment
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerFragment

class EncryptRouter(private val activity: EncryptActivity) {

	fun navigateToFilesPicker() {
		val fragment = FilesPickerFragment()
		fragment.setOutput(activity)
		navigateToFragment(fragment, false)
	}

	fun finish() {
		activity.finish()
	}

	private fun navigateToFragment(fragment: Fragment, addToBackStack: Boolean = true) {
		val transaction = activity.supportFragmentManager.beginTransaction()
		if (addToBackStack) {
			transaction.addToBackStack(null)
		}
		transaction.replace(R.id.fragmentContainer, fragment)
		transaction.commit()
	}
}