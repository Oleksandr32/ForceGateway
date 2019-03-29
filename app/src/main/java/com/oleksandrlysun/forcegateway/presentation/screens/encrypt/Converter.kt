package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import androidx.databinding.BindingConversion
import androidx.databinding.InverseMethod
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm

object Converter {

	@JvmStatic
	@BindingConversion
	fun convertCryptoAlgorithmsToTitles(algorithms: Array<CryptoAlgorithm>): List<String> {
		return algorithms.map(CryptoAlgorithm::name)
	}

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