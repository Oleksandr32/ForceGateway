package com.oleksandrlysun.forcegateway.presentation.screens.filespicker

import androidx.appcompat.app.AppCompatActivity
import com.oleksandrlysun.forcegateway.R

class FilesPickerRouter(private val activity: AppCompatActivity) {

	fun navigateToFilesPicker(path: String? = null) {
		val fragment = FilesPickerFragment.newInstance(path)
		activity.supportFragmentManager.beginTransaction().run {
			addToBackStack(path)
			replace(R.id.fragmentContainer, fragment)
			commit()
		}
	}

	fun finish() {
		activity.finish()
	}
}