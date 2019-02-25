package com.oleksandrlysun.forcegateway.domain.interactors

import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StorageInteractor @Inject constructor(private val storageService: StorageService) {

	fun getFiles(path: String?): Single<List<FileModel>> {
		return storageService.run {
			path?.let { getFiles(it) } ?: getFiles()
		}.subscribeOn(Schedulers.io())
	}
}