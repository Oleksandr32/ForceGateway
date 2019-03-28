package com.oleksandrlysun.forcegateway.extensions

inline fun <reified T : Enum<T>> T.next(): T? {
	val values = enumValues<T>()
	if (ordinal >= values.size - 1) {
		return null
	}

	return values[ordinal + 1]
}

inline fun <reified T : Enum<T>> T.prev(): T? {
	val values = enumValues<T>()
	if (ordinal <= 0) {
		return null
	}

	return values[ordinal - 1]
}