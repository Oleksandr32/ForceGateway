package com.oleksandrlysun.forcegateway.data.crypto

import java.io.File
import java.security.Key

abstract class CryptoCipher {

    abstract val algorithm: String

    abstract fun generateKey(seed: String, keySize: Int): Key

    abstract fun encrypt(file: File, secretKey: Key)

    abstract fun decrypt(file: File, secretKey: Key)
}