package com.oleksandrlysun.forcegateway.presentation.permissions

interface StoragePermissionsDelegate {

	fun hasStoragePermissions(): Boolean

	fun requestStoragePermissions()
}