package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import javax.inject.Inject

@ActivityScope
class EncryptPresenter @Inject constructor(
		private val view: EncryptView,
		private val router: EncryptRouter
) {

	init {
		router.navigateToFilesPicker()
	}

	fun onStoragePermissionsDenied() {
		router.finish()
	}

	fun onFilesPick(files: List<FileModel>) {
		TODO()
	}
}