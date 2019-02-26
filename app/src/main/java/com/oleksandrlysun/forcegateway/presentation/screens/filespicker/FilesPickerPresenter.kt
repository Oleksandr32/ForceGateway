package com.oleksandrlysun.forcegateway.presentation.screens.filespicker

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.domain.interactors.StorageInteractor
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.extensions.uiThread
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilePickerState.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@ActivityScope
class FilesPickerPresenter @Inject constructor(
		private val router: FilesPickerRouter,
		private val view: FilesPickerView?,
		private val storagePermissionsDelegate: StoragePermissionsDelegate,
		private val storageInteractor: StorageInteractor
) {

	private val mDisposables = CompositeDisposable()
	private var mPath: String? = null

	init {
		checkPermissions()
	}

	fun onStoragePermissionsGranted() {
		router.navigateToFilesPicker()
		storageInteractor.getFiles(mPath)
				.uiThread()
				.subscribeBy(
						onSuccess = { files ->
							if (files.isEmpty()) {
								view?.setFilesPickerState(EMPTY)
							} else {
								view?.setFilesPickerState(FETCHED)
								view?.setFiles(files)
							}
						},
						onError = Throwable::printStackTrace
				)
				.addTo(mDisposables)
	}

	fun onFileClick(fileModel: FileModel) {
		router.navigateToFilesPicker(fileModel.path)
	}

	fun onPathChanged(newPath: String?) {
		mPath = newPath
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