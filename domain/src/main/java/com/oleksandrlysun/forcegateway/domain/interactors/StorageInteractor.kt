package com.oleksandrlysun.forcegateway.domain.interactors

import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StorageInteractor @Inject constructor(private val storageService: StorageService) {

	companion object {

		private const val PATH = "Force Gateway Storage"
	}

	fun getFiles(): Single<List<FileModel>> {
		if (!storageService.isFolderExists(PATH)) {
			storageService.createFolder(PATH)
		}
		return storageService.getFiles(PATH)
				.subscribeOn(Schedulers.io())
	}
}