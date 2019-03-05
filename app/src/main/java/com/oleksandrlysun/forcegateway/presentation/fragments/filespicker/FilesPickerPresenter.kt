package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.domain.interactors.StorageInteractor
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.extensions.uiThread
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilePickerState.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@FragmentScope
class FilesPickerPresenter @Inject constructor(
		private val view: FilesPickerView,
		private val storagePermissionsDelegate: StoragePermissionsDelegate,
		private val storageInteractor: StorageInteractor
) : FilesPickerListener {

	private val disposables = CompositeDisposable()

	init {
		checkPermissions()
	}

	override fun onFileClick(fileModel: FileModel) {
		TODO()
	}

	override fun onFileLongClick(fileModel: FileModel) {
		TODO()
	}

	fun onStoragePermissionsGranted() {
		storageInteractor.getFiles()
				.uiThread()
				.subscribeBy(
						onSuccess = { files ->
							if (files.isEmpty()) {
								view.setFilesPickerState(EMPTY)
							} else {
								view.setFilesPickerState(FETCHED)
								view.setFiles(files)
							}
						},
						onError = Throwable::printStackTrace
				)
				.addTo(disposables)
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