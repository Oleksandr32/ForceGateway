package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import java.io.File

interface FilesPickerView {

	fun setFiles(files: List<File>)

	fun setFilesPickerState(state: FilePickerState)

	fun setResult(files: List<File>)
}