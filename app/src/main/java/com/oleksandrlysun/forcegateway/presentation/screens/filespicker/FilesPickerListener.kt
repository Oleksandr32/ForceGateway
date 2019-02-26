package com.oleksandrlysun.forcegateway.presentation.screens.filespicker

import com.oleksandrlysun.forcegateway.domain.models.FileModel

interface FilesPickerListener {

	fun onFileClick(fileModel: FileModel)

	fun onFileLongClick(fileModel: FileModel)
}