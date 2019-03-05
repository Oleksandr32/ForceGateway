package com.oleksandrlysun.forcegateway.domain.boundaries

import com.oleksandrlysun.forcegateway.domain.models.FileModel
import io.reactivex.Single

interface StorageService {

	fun isFolderExists(path: String): Boolean

	fun createFolder(path: String)

	fun getFiles(path: String): Single<List<FileModel>>
}