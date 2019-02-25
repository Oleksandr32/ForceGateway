package com.oleksandrlysun.forcegateway.domain.boundaries

import com.oleksandrlysun.forcegateway.domain.models.FileModel
import io.reactivex.Single

interface StorageService {

	fun getFiles(): Single<List<FileModel>>

	fun getFiles(path: String): Single<List<FileModel>>
}