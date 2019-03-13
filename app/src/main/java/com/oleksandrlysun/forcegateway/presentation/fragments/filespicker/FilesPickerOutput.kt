package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import java.io.File

interface FilesPickerOutput {

	fun onFilesPick(files: List<File>)

	fun onStoragePermissionDenied()
}