package com.oleksandrlysun.forcegateway.data.crypto

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import androidx.annotation.RequiresPermission
import java.io.File
import java.security.Key
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.CipherOutputStream
import javax.crypto.KeyGenerator

class AESCipher : CryptoCipher() {

	override val algorithm = "AES"

	override fun generateKey(seed: String, keySize: Int): Key {
		val secureRandom = SecureRandom(seed.toByteArray())
		val keyGenerator = KeyGenerator.getInstance(algorithm)
		keyGenerator.init(keySize, secureRandom)

		return keyGenerator.generateKey()
	}

	@RequiresPermission(allOf = [READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE])
	override fun encrypt(file: File, secretKey: Key) {
		val fileInput = file.inputStream()
		val fileOutput = file.outputStream()

		val cipher = Cipher.getInstance(algorithm)
		cipher.init(Cipher.ENCRYPT_MODE, secretKey)

		val output = CipherOutputStream(fileOutput, cipher)
	}

	@RequiresPermission(allOf = [READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE])
	override fun decrypt(file: File, secretKey: Key) {
		val fileInput = file.inputStream()
		val fileOutput = file.outputStream()

		val cipher = Cipher.getInstance(algorithm)
		cipher.init(Cipher.DECRYPT_MODE, secretKey)
		cipher.doFinal()

		val output = CipherOutputStream(fileOutput, cipher)
	}
}