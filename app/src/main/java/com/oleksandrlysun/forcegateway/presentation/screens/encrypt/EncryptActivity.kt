package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptComponent
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptModule
import javax.inject.Inject

class EncryptActivity : AppCompatActivity(), EncryptView, StoragePermissionsDelegate {

	companion object {

		private const val STORAGE_PERMISSION_REQUEST_CODE = 0
	}

	@Inject
	lateinit var presenter: EncryptPresenter

	internal lateinit var component: EncryptComponent

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injectDependencies()
		setContentView(R.layout.activity_encrypt)
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == STORAGE_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
			TODO()
		} else {
			finish()
		}
	}

	override fun hasStoragePermissions(): Boolean {
		val permissions = arrayOf(
				Manifest.permission.READ_EXTERNAL_STORAGE,
				Manifest.permission.WRITE_EXTERNAL_STORAGE)
		return permissions.all { permission ->
			ContextCompat.checkSelfPermission(this, permission) == PERMISSION_GRANTED
		}
	}

	override fun requestStoragePermissions() {
		val permissions = arrayOf(
				Manifest.permission.READ_EXTERNAL_STORAGE,
				Manifest.permission.WRITE_EXTERNAL_STORAGE)
		ActivityCompat.requestPermissions(this, permissions, STORAGE_PERMISSION_REQUEST_CODE)
	}

	private fun injectDependencies() {
		val module = EncryptModule(this)
		component = applicationComponent.getEncryptComponent(module)
		component.inject(this)
	}
}