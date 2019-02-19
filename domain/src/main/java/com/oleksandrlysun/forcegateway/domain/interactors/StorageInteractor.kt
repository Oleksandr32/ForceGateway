package com.oleksandrlysun.forcegateway.domain.interactors

import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.File
import javax.inject.Inject

class StorageInteractor @Inject constructor(private val storageService: StorageService) {

	fun getAllFiles(): Single<List<File>> {
		return storageService.getAllFiles()
				.subscribeOn(Schedulers.io())
	}
}