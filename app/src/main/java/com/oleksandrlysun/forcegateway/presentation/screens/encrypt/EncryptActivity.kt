package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerOutput
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptComponent
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptModule
import javax.inject.Inject

class EncryptActivity : AppCompatActivity(), EncryptView, FilesPickerOutput {

	@Inject
	lateinit var presenter: EncryptPresenter

	internal lateinit var component: EncryptComponent

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injectDependencies()
		setContentView(R.layout.activity_encrypt)
	}

	override fun onStoragePermissionsDenied() {
		presenter.onStoragePermissionsDenied()
	}

	private fun injectDependencies() {
		val module = EncryptModule(this)
		component = applicationComponent.getEncryptComponent(module)
		component.inject(this)
	}
}