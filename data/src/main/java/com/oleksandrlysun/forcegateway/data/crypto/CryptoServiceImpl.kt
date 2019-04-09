package com.oleksandrlysun.forcegateway.data.crypto

import android.annotation.SuppressLint
import com.oleksandrlysun.forcegateway.domain.boundaries.CryptoService
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.security.Key

@SuppressLint("MissingPermission")
class CryptoServiceImpl : CryptoService {

	override fun generateKey(algorithm: CryptoAlgorithm, seed: String, keySize: Int): Single<Key> {
		return Single.fromCallable {
			val cipher = CryptoCipherFactory.create(algorithm)
			return@fromCallable cipher.generateKey(seed, keySize)
		}
	}

	override fun encryptFile(file: File, algorithm: CryptoAlgorithm, secretKey: Key): Completable {
		return Completable.fromCallable {
			val cipher = CryptoCipherFactory.create(algorithm)
			return@fromCallable cipher.encrypt(file, secretKey)
		}
	}

	override fun decryptFile(file: File, algorithm: CryptoAlgorithm, secretKey: Key): Completable {
		return Completable.fromCallable {
			val cipher = CryptoCipherFactory.create(algorithm)
			return@fromCallable cipher.decrypt(file, secretKey)
		}
	}
}