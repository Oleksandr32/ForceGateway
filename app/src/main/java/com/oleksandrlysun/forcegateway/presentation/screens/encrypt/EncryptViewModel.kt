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
import java.security.Key
import javax.inject.Inject
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.roundToInt

@ActivityScope
class EncryptViewModel @Inject constructor(
		private val view: EncryptView,
		private val router: EncryptRouter,
		private val cryptoInteractor: CryptoInteractor
) : BaseObservable() {

	companion object {

		private const val DEFAULT_KEY_SIZE = 64
		private const val DISCRETE_KEY_SIZE = 2.0
	}

	@get:Bindable
	var currentStep by ObservableField(fieldId = BR.currentStep, value = PICK_FILES)

	@get:Bindable
	var pickedFiles by ObservableField(fieldId = BR.pickedFiles, value = emptyList<File>())

	@get:Bindable
	var algorithm by ObservableField(fieldId = BR.algorithm, value = CryptoAlgorithm.AES)

	@get:Bindable
	var keySize by ObservableField(fieldId = BR.keySize, value = DEFAULT_KEY_SIZE)

	@get:Bindable
	var seed by ObservableField(fieldId = BR.seed, value = "")

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
			PICK_FILES, SETTINGS -> pickedFiles.isNotEmpty()
		}
	}

	@Bindable
	fun getAlgorithmsNames(): List<String> {
		return CryptoAlgorithm.values().map(CryptoAlgorithm::name)
	}

	private val disposables = CompositeDisposable()
	private var key: Key? = null

	init {
		router.navigateToFilesPicker()
	}

	fun goToNextStep() {
		when (currentStep) {
			PICK_FILES -> router.navigateToEncryptSettings()
			SETTINGS -> generateKey()
		}
		currentStep = currentStep.next() ?: SETTINGS
	}

	fun goToPreviousStep() {
		currentStep = currentStep.prev() ?: PICK_FILES
	}

	fun onStoragePermissionsDenied() {
		router.finish()
	}

	fun onFilesPick(files: List<File>) {
		pickedFiles = files
	}

	fun onKeySizeChanged() {
		val coerceKeySize = keySize.coerceAtLeast(DEFAULT_KEY_SIZE).toDouble()
		val discrete = log2(coerceKeySize).roundToInt()
		keySize = DISCRETE_KEY_SIZE.pow(discrete.toDouble()).toInt()
	}

	private fun generateKey() {
		cryptoInteractor.generateKey(algorithm, keySize, seed)
				.uiThread()
				.subscribeBy(
						onSuccess = {
							key = it
						},
						onError = Throwable::printStackTrace
				)
				.addTo(disposables)
	}
}