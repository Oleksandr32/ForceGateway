package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import java.io.File

data class SelectableFileItem(val file: File, var isSelected: Boolean)

object SelectableFileItemFactory {

	@JvmStatic
	fun create(file: File, isSelected: Boolean): SelectableFileItem {
		return SelectableFileItem(file, isSelected)
	}

	@JvmStatic
	fun create(files: List<File>, isSelected: Boolean): List<SelectableFileItem> {
		return files.map { create(it, isSelected) }
	}
}