package com.oleksandrlysun.forcegateway.domain.boundaries

import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.security.Key

interface CryptoService {

	fun generateKey(algorithm: CryptoAlgorithm, seed: String, keySize: Int): Single<Key>

	fun encryptFile(file: File, algorithm: CryptoAlgorithm, secretKey: Key): Completable

	fun decryptFile(file: File, algorithm: CryptoAlgorithm, secretKey: Key): Completable
}