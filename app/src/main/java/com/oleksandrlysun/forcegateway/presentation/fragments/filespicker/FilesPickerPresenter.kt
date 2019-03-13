package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.domain.interactors.StorageInteractor
import com.oleksandrlysun.forcegateway.extensions.uiThread
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilePickerState.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.io.File
import javax.inject.Inject

@FragmentScope
class FilesPickerPresenter @Inject constructor(
		private val view: FilesPickerView,
		private val storagePermissionsDelegate: StoragePermissionsDelegate,
		private val storageInteractor: StorageInteractor
) : FilesPickerListener {

	private var files = emptyList<File>()
	private val disposables = CompositeDisposable()

	init {
		checkPermissions()
	}

	override fun onFileClick(fileModel: File) {
		TODO()
	}

	override fun onFileLongClick(fileModel: File) {
		TODO()
	}

	fun onStoragePermissionsGranted() {
		storageInteractor.getFiles()
				.doOnSuccess { files = it }
				.uiThread()
				.subscribeBy(
						onSuccess = { files ->
							if (files.isEmpty()) {
								view.setFilesPickerState(EMPTY)
							} else {
								view.setFilesPickerState(PRESENT)
								view.setFiles(files)
							}
						},
						onError = Throwable::printStackTrace
				)
				.addTo(disposables)
	}

	fun search(query: String) {
		if (files.isEmpty()) {
			return
		}

		files.toObservable()
				.filter { file -> file.name.contains(query, ignoreCase = true) }
				.toList()
				.subscribeBy { searchedFiles ->
					if (searchedFiles.isEmpty()) {
						view.setFilesPickerState(NOT_FOUND_RESULT)
					} else {
						view.setFiles(searchedFiles)
						view.setFilesPickerState(PRESENT)
					}
				}
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