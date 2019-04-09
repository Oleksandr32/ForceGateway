package com.oleksandrlysun.forcegateway.domain.interactors

import com.oleksandrlysun.forcegateway.domain.boundaries.CryptoService
import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.security.Key
import javax.inject.Inject

class CryptoInteractor @Inject constructor(
		private val storageService: StorageService,
		private val cryptoService: CryptoService
) {

	fun generateKey(algorithm: CryptoAlgorithm, keySize: Int, seed: String): Single<Key> {
		return cryptoService.generateKey(algorithm, seed, keySize)
				.subscribeOn(Schedulers.io())
	}

	fun encryptFiles(files: List<File>): Completable {
		TODO()
	}

	fun decryptFiles(files: List<File>): Completable {
		TODO()
	}
}