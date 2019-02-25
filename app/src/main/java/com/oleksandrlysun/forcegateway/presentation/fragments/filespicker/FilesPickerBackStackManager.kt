package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.domain.models.FileModel

class FilesPickerBackStackManager {

	interface Listener {

		fun onStackChanged(files: List<FileModel>)
	}

	var listener: Listener? = null

	private var mFiles = mutableListOf<FileModel>()

	val top: FileModel
		get() = mFiles[mFiles.size - 1]

	fun addToStack(fileModel: FileModel) {
		mFiles.add(fileModel)
		listener?.onStackChanged(mFiles)
	}

	fun popFromStack() {
		if (mFiles.isNotEmpty()) {
			mFiles.removeAt(mFiles.size - 1)
		}
		listener?.onStackChanged(mFiles)
	}

	fun popFromStackTill(fileModel: FileModel) {
		mFiles = mFiles.subList(0, mFiles.indexOf(fileModel) + 1)
		listener?.onStackChanged(mFiles)
	}
}