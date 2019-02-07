package com.oleksandrlysun.forcegateway.utils

fun <T> lazyUnsynchronized(initializer: () -> T): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE, initializer)
}