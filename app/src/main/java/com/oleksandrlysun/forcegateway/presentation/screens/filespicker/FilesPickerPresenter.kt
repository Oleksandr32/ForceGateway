package com.oleksandrlysun.forcegateway.presentation.screens.filespicker

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.domain.interactors.StorageInteractor
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.domain.models.FileType
import com.oleksandrlysun.forcegateway.extensions.uiThread
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilePickerState.*
import com.oleksandrlysun.forcegateway.utils.lazyUnsynchronized
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class FilesPickerPresenter @Inject constructor(
		private val router: FilesPickerRouter,
		viewProvider: Provider<FilesPickerView>,
		private val storagePermissionsDelegate: StoragePermissionsDelegate,
		private val storageInteractor: StorageInteractor
) : FilesPickerListener {

	private val view by lazyUnsynchronized { viewProvider.get() }

	private var mPath: String? = null
	private val mDisposables = CompositeDisposable()

	init {
		checkPermissions()
	}

	override fun onFileClick(fileModel: FileModel) {
		if (fileModel.type == FileType.FOLDER) {
			router.navigateToFilesPicker(fileModel.path)
		}
	}

	override fun onFileLongClick(fileModel: FileModel) {

	}

	fun onStoragePermissionsGranted() {
		router.navigateToFilesPicker()
	}

	fun getFiles() {
		storageInteractor.getFiles(mPath)
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
				.addTo(mDisposables)
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