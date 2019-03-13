package com.oleksandrlysun.forcegateway.domain.boundaries

import io.reactivex.Single
import java.io.File

interface StorageService {

	fun isFolderExists(path: String): Boolean

	fun createFolder(path: String)

	fun getFiles(path: String): Single<List<File>>
}