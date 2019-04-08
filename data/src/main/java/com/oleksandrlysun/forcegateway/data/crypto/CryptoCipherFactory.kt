package com.oleksandrlysun.forcegateway.data.crypto

import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm.*

object CryptoCipherFactory {

	fun create(algorithm: CryptoAlgorithm): CryptoCipher {
		return when (algorithm) {
			AES -> AESCipher()
			DES -> AESCipher()
			RSA -> AESCipher()
		}
	}
}