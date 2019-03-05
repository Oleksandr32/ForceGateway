package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.domain.models.FileModel

interface FilesPickerListener {

	fun onFileClick(fileModel: FileModel)

	fun onFileLongClick(fileModel: FileModel)
}