package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import com.oleksandrlysun.forcegateway.domain.models.FileModel

interface FilesPickerOutput {

	fun onFilesPick(files: List<FileModel>)

	fun onStoragePermissionDenied()
}