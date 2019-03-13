package com.oleksandrlysun.forcegateway.domain.interactors

import com.oleksandrlysun.forcegateway.domain.boundaries.CryptoService
import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import io.reactivex.Completable
import java.io.File
import javax.inject.Inject

class CryptoInteractor @Inject constructor(
		private val storageService: StorageService,
		private val cryptoService: CryptoService
) {

	companion object {

		private const val SECRET_PATH = "Secret"
	}

	fun encryptFiles(files: List<File>): Completable {
		TODO()
	}

	fun decryptFiles(files: List<File>): Completable {
		TODO()
	}
}