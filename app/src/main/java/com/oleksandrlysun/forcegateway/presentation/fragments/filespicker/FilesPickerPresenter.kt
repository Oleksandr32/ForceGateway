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
import javax.inject.Inject

@FragmentScope
class FilesPickerPresenter @Inject constructor(
		private val view: FilesPickerView,
		private val storagePermissionsDelegate: StoragePermissionsDelegate,
		private val storageInteractor: StorageInteractor
) : FilesPickerListener {

	private var fileItems = emptyList<SelectableFileItem>()
	private val disposables = CompositeDisposable()

	init {
		checkPermissions()
	}

	override fun onFileItemClick(fileItem: SelectableFileItem) {
		val newFileItem = SelectableFileItem(fileItem.file, !fileItem.isSelected)
		fileItems.replace(fileItem, newFileItem)
		view.setFiles(fileItems)
	}

	fun onStoragePermissionsGranted() {
		storageInteractor.getFiles()
				.map { SelectableFileItemFactory.create(it, false) }
				.doOnSuccess { fileItems = it }
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
		if (fileItems.isEmpty()) {
			return
		}

		fileItems.toObservable()
				.filter { it.file.name.contains(query, ignoreCase = true) }
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

	fun onContinueClick() {
		val result = fileItems.asSequence()
				.filter { it.isSelected }
				.map(SelectableFileItem::file)
				.toList()

		view.setResult(result)
	}

	private fun checkPermissions() {
		val hasPermissions = storagePermissionsDelegate.hasStoragePermissions()
		if (!hasPermissions) {
			storagePermissionsDelegate.requestStoragePermissions()
		} else {
			onStoragePermissionsGranted()
		}
	}

	private fun <T> Iterable<T>.replace(old: T, new: T): Iterable<T> {
		return map { if (it == old) new else it }
	}
}