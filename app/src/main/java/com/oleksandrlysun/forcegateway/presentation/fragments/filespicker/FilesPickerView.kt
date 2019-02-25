package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.domain.models.FileModel

interface FilesPickerView {

	fun setFiles(files: List<FileModel>)

	fun setFilesPickerState(state: FilePickerState)
}