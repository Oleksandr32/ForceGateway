package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.domain.interactors.StorageInteractor
import com.oleksandrlysun.forcegateway.extensions.uiThread
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@FragmentScope
class FilesPickerPresenter @Inject constructor(
		private val view: FilesPickerView,
		private val storagePermissionsDelegate: StoragePermissionsDelegate,
		private val storageInteractor: StorageInteractor
) {

	private val mDisposables = CompositeDisposable()

	init {
		checkPermissions()
	}

	fun onStoragePermissionsGranted() {
		storageInteractor.getAllFiles()
				.uiThread()
				.subscribeBy(
						onSuccess = { files ->
							view.setFiles(files)
						},
						onError = {
							it.printStackTrace()
						}
				).addTo(mDisposables)
	}

	private fun checkPermissions() {
		val hasPermissions = storagePermissionsDelegate.hasStoragePermissions()
		if (!hasPermissions) {
			storagePermissionsDelegate.requestStoragePermissions()
		} else {
			onStoragePermissionsGranted()
		}
	}
}