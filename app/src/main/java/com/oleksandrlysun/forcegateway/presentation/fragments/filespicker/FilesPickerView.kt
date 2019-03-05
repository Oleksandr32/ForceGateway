package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilePickerState

interface FilesPickerView {

	fun setFiles(files: List<FileModel>)

	fun setFilesPickerState(state: FilePickerState)
}