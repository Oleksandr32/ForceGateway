package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.domain.interactors.CryptoInteractor
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import com.oleksandrlysun.forcegateway.extensions.uiThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.io.File
import javax.inject.Inject

@ActivityScope
class EncryptPresenter @Inject constructor(
		private val view: EncryptView,
		private val router: EncryptRouter,
		private val cryptoInteractor: CryptoInteractor
) {

	private var algorithm = CryptoAlgorithm.AES
	private val disposables = CompositeDisposable()

	init {
		router.navigateToFilesPicker()
	}

	fun onStoragePermissionsDenied() {
		router.finish()
	}

	fun onFilesPick(files: List<File>) {
		router.navigateToEncryptSettings()
	}

	fun onAlgorithmTabSelect(name: String) {
		algorithm = CryptoAlgorithm.valueOf(name)
	}

	fun generateKey(keySize: Int, seed: String) {
		cryptoInteractor.generateKey(algorithm, keySize, seed)
				.uiThread()
				.subscribeBy(
						onSuccess = {

						},
						onError = Throwable::printStackTrace
				).addTo(disposables)
	}
}