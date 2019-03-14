package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extensions.bindView
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerOutput
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptComponent
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptModule
import java.io.File
import javax.inject.Inject

class EncryptActivity : AppCompatActivity(), EncryptView, FilesPickerOutput {

	@Inject
	lateinit var presenter: EncryptPresenter

	internal lateinit var component: EncryptComponent

	private val toolbar by bindView<Toolbar>(R.id.toolbar)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injectDependencies()
		setContentView(R.layout.activity_encrypt)
		setupToolbar()
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return super.onSupportNavigateUp()
	}

	override fun onFilesPick(files: List<File>) {
		presenter.onFilesPick(files)
	}

	override fun onStoragePermissionDenied() {
		presenter.onStoragePermissionsDenied()
	}

	private fun injectDependencies() {
		val module = EncryptModule(this)
		component = applicationComponent.getEncryptComponent(module)
		component.inject(this)
	}

	private fun setupToolbar() {
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}
}