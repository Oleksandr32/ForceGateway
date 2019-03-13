package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import java.io.File

interface FilesPickerListener {

	fun onFileClick(fileModel: File)

	fun onFileLongClick(fileModel: File)
}