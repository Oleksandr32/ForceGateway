package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.oleksandrlysun.forcegateway.BR
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.domain.interactors.CryptoInteractor
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import com.oleksandrlysun.forcegateway.extensions.next
import com.oleksandrlysun.forcegateway.extensions.prev
import com.oleksandrlysun.forcegateway.extensions.uiThread
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.EncryptStep.*
import com.oleksandrlysun.forcegateway.utils.ObservableField
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.io.File
import javax.inject.Inject

@ActivityScope
class EncryptViewModel @Inject constructor(
		private val view: EncryptView,
		private val router: EncryptRouter,
		private val cryptoInteractor: CryptoInteractor
) : BaseObservable() {

	@get:Bindable
	var currentStep by ObservableField(fieldId = BR.currentStep, value = PICK_FILES)

	@get:Bindable
	var pickedFiles by ObservableField(fieldId = BR.pickedFiles, value = emptyList<File>())

	@Bindable("currentStep")
	fun getBottomButtonResId(): Int {
		return when (currentStep) {
			PICK_FILES -> R.string.btn_continue
			SETTINGS -> R.string.btn_generate_key
		}
	}

	@Bindable("currentStep", "pickedFiles")
	fun isBottomButtonEnabled(): Boolean {
		return when (currentStep) {
			PICK_FILES -> pickedFiles.isNotEmpty()
			SETTINGS -> false
		}
	}

	private var algorithm = CryptoAlgorithm.AES
	private val disposables = CompositeDisposable()

	init {
		router.navigateToFilesPicker()
	}

	fun onStoragePermissionsDenied() {
		router.finish()
	}

	fun onFilesPick(files: List<File>) {
		pickedFiles = files
	}

	fun goToNextStep() {
		when (currentStep) {
			PICK_FILES -> router.navigateToEncryptSettings()
			SETTINGS -> TODO()
		}
		currentStep = currentStep.next() ?: SETTINGS
	}

	fun goToPreviousStep() {
		currentStep = currentStep.prev() ?: PICK_FILES
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
				)
				.addTo(disposables)
	}
}