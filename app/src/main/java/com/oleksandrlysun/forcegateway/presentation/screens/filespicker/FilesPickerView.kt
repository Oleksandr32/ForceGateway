package com.oleksandrlysun.forcegateway.presentation.screens.filespicker

import com.oleksandrlysun.forcegateway.domain.models.FileModel

interface FilesPickerView {

	fun setFiles(files: List<FileModel>)

	fun setFilesPickerState(state: FilePickerState)
}