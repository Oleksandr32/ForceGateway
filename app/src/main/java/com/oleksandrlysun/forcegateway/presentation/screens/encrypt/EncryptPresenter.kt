package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import javax.inject.Inject

@ActivityScope
class EncryptPresenter @Inject constructor(
		private val view: EncryptView,
		private val storagePermissionsDelegate: StoragePermissionsDelegate,
		private val storageService: StorageService
) {

	init {
		checkPermissions()
	}

	private fun checkPermissions() {
		val hasPermissions = storagePermissionsDelegate.hasStoragePermissions()
		if (!hasPermissions) {
			storagePermissionsDelegate.requestStoragePermissions()
		}
	}
}