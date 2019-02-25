package com.oleksandrlysun.forcegateway.domain.models

data class FileModel(
		val path: String,
		val type: FileType,
		val name: String,
		val sizeInMB: Double,
		val extension: String = "",
		val subFiles: Int = 0
)