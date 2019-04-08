package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import androidx.databinding.InverseMethod
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm

object Converter {

	@JvmStatic
	@InverseMethod("convertPositionToCryptoAlgorithm")
	fun convertCryptoAlgorithmToPosition(algorithm: CryptoAlgorithm): Int {
		return algorithm.ordinal
	}

	@JvmStatic
	fun convertPositionToCryptoAlgorithm(position: Int): CryptoAlgorithm {
		val algorithms = CryptoAlgorithm.values()
		return algorithms[position]
	}
}